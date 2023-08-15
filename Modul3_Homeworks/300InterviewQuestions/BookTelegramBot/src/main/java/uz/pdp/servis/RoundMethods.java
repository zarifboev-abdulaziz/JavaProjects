package uz.pdp.servis;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.MyBot;
import uz.pdp.model.*;
import uz.pdp.model.enums.Type;
import uz.pdp.utils.DataBase;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class RoundMethods {
    HelperMethods helperMethods = new HelperMethods();

    //Round 0
    public void registerProcess(User currentUser, Update update){
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());

        if (currentUser.getPhoneNumber() == null){

            if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")){
                helperMethods.sendMessage(currentUser, "Assalomu Alayakum Botga Hush Kelibsiz! \n" +
                        "Bot Xizmatlaridan foydalanish uchun iltimos telefon raqamingizni jo'nating!");
            }
            userActivity.setRound(1);
        } else {

            if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")){
                userActivity.setRound(1);
                helperMethods.sendMessage(currentUser, "Asosiy Menu");
                userActivity.setRound(2);
            }
        }


    }


    //round 1
    public void confirmContact(User currentUser, Update update) {
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());
        if (update.getMessage().hasContact()){
            Contact contact = update.getMessage().getContact();
            currentUser.setPhoneNumber(contact.getPhoneNumber());
        }

        helperMethods.sendMessage(currentUser, "Asosiy Menu");
        userActivity.setRound(2);

    }

    //round 2
    public void selectFromMainMenu(User currentUser, Update update) {
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());
        String text = update.getMessage().getText();

        switch (text){
            case "Kitob sotib olish":
                helperMethods.sendMessage(currentUser, "Kategoriyani tanlang: ");
                userActivity.setRound(3);
                break;
            case "Sotib olingan kitoblar":
                sendUserBooks(currentUser);
                break;
            case "Haridlar tarixi":
                sendUserOrderHistory(currentUser);
                break;
            default:
                break;
        }
    }

    private void sendUserOrderHistory(User currentUser) {
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());
        long count = DataBase.orderHistoryList.stream().filter(orderHistory -> orderHistory.getUser().getId().equals(currentUser.getId())).count();
        if (count == 0){
            userActivity.setRound(8);
            helperMethods.sendMessage(currentUser, "Hali kitob sotib olmagansiz!");
            return;
        }

        File file = new File("src/main/resources/userOrderBookHistory.pdf");

        try (PdfWriter writer = new PdfWriter(file)) {
            PdfDocument pdfDocument = new PdfDocument(writer);
            pdfDocument.setDefaultPageSize(PageSize.A3);
            pdfDocument.addNewPage();
            Document document = new Document(pdfDocument);

            Paragraph paragraph1 = new Paragraph("Sotib olingan kitoblar ro'yhati!");
            paragraph1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(paragraph1);


            float[] pointColWidth = {150F, 150F, 150F, 150F};
            Table table = new Table(pointColWidth);

            table.addCell("T/R");
            table.addCell("Kitob");
            table.addCell("To'lov Turi");
            table.addCell("Sanasi");

            for (int i = 0; i < DataBase.orderHistoryList.size(); i++) {
                OrderHistory orderHistory = DataBase.orderHistoryList.get(i);
                if (orderHistory.getUser().getId().equals(currentUser.getId())){
                    table.addCell(String.valueOf(i + 1));
                    table.addCell(orderHistory.getBook().getName());
                    table.addCell(String.valueOf(orderHistory.getPayType().getName()));
                    table.addCell(String.valueOf(orderHistory.getDateTime()));
                }
            }

            document.add(table);
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        MyBot myBot = new MyBot();
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(currentUser.getChatId());
        sendDocument.setDocument(new InputFile(file));
        sendDocument.setCaption("Sotib olingan kitoblar ro'yhati!");

        try {
            myBot.execute(sendDocument);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        userActivity.setRound(1);
        helperMethods.sendMessage(currentUser, "Asosiy Menu");
        userActivity.setRound(2);

    }

    private void sendUserBooks(User currentUser) {
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());
        MyBot myBot = new MyBot();
        userActivity.setRound(8);
        helperMethods.sendMessage(currentUser, "Kitoblar Ro'yhati!");


        for (Book myBook : currentUser.getMyBooks()) {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(currentUser.getChatId());
            sendDocument.setDocument(new InputFile(new File(myBook.getBookPath())));
            sendDocument.setCaption(myBook.getName());

            try {
                myBot.execute(sendDocument);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        userActivity.setRound(1);
        helperMethods.sendMessage(currentUser, "Asosiy Menu");
        userActivity.setRound(2);
    }

    //round 3
    public void selectingCategory(User currentUser, Update update) {
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());
        String text = update.getMessage().getText();
        userActivity.setCategory(text);

        String booksInfo = "";
        for (int i = 0; i < DataBase.bookList.size(); i++) {
            Book book = DataBase.bookList.get(i);
            if (book.getCategory().equals(text)){
                booksInfo += (i+1) + ". " + book.getAuthor() + " | " + book.getName() + " | " + book.getType() + "\n";
            }
        }

        helperMethods.sendMessage(currentUser, booksInfo);
        userActivity.setRound(4);
    }

    //round 4
    public void selectingBook(User currentUser, Update update) {
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());
        String text = update.getMessage().getText();

        Book book = DataBase.bookList.get(Integer.parseInt(text) - 1);
        userActivity.setSelectedBook(book.getName());

        String  bookInfo = "Avtor: " + book.getAuthor() + "\n" +
                "Nomi: " + book.getName()  + "\n" +
                "Narxi: "  + book.getPrice()  + "\n" +
                "Kategoriyasi: " + book.getCategory() + "\n" +
                "Formati: "  + book.getType()  + "\n";

        helperMethods.sendMessage(currentUser, bookInfo);
        userActivity.setRound(5);

    }

    //round 5
    public void deciding(User currentUser, Update update) {
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());
        String text = update.getMessage().getText();

        if (text.equals("Asosiy Menu")){
            userActivity.setRound(1);
            helperMethods.sendMessage(currentUser, "Asosiy Menu");
            userActivity.setRound(2);

        } else if (text.equals("Sotib Olish")){
            helperMethods.sendMessage(currentUser, "To'lov turini tanlang: ");
            userActivity.setRound(6);
        }

    }

    public void selectingPayType(User currentUser, Update update) {
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());
        String text = update.getMessage().getText();
        userActivity.setSelectedPayType(text);

        PayType selectedPayType = null;
        for (PayType payType : DataBase.payTypeList) {
            if (payType.getName().equalsIgnoreCase(text)){
                selectedPayType = payType;
            }
        }

        Book selectedBook = null;
        for (Book book : DataBase.bookList) {
            if (book.getName().equalsIgnoreCase(userActivity.getSelectedBook())){
                selectedBook = book;
            }
        }

        double commission = selectedBook.getPrice() * (selectedPayType.getCommissionFee()/100);

        String info = "Avtor: " + selectedBook.getAuthor()  + "\n" +
                "Nomi: "  + selectedBook.getName()  + "\n" +
                "Komissiya: "  + commission  + "\n" +
                "Umumiy summasi: " + (selectedBook.getPrice() + commission) + "\n";

        helperMethods.sendMessage(currentUser, info);
        userActivity.setRound(7);
    }

    public void deciding2(User currentUser, Update update) {
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());
        String text = update.getMessage().getText();

        if (text.equals("Asosiy Menu")){
            userActivity.setRound(1);
            helperMethods.sendMessage(currentUser, "Asosiy Menu");
            userActivity.setRound(2);

        } else if (text.equals("Sotib Olish")){

            PayType selectedPayType = null;
            for (PayType payType : DataBase.payTypeList) {
                if (payType.getName().equalsIgnoreCase(userActivity.getSelectedPayType())){
                    selectedPayType = payType;
                }
            }

            Book selectedBook = null;
            for (Book book : DataBase.bookList) {
                if (book.getName().equalsIgnoreCase(userActivity.getSelectedBook())){
                    selectedBook = book;
                }
            }
            if (selectedBook.getType() == Type.PrintedBook){
                helperMethods.sendMessage(currentUser, "Lokatsiyani tashlang!");
                userActivity.setRound(8);
            } else {
                MyBot myBot = new MyBot();
                userActivity.setRound(8);
                helperMethods.sendMessage(currentUser, "Muvaffaqqiyatli Sotib olindi!");
                SendDocument sendDocument = new SendDocument();
                sendDocument.setChatId(currentUser.getChatId());
                sendDocument.setDocument(new InputFile(new File(selectedBook.getBookPath())));
                sendDocument.setCaption(selectedBook.getName());

                try {
                    myBot.execute(sendDocument);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                userActivity.setRound(1);
                helperMethods.sendMessage(currentUser, "Asosiy Menu");
                userActivity.setRound(2);

                recordToSystem(currentUser, selectedBook, selectedPayType);
            }

        }
    }

    private void recordToSystem(User currentUser, Book selectedBook, PayType selectedPayType) {
        currentUser.getMyBooks().add(selectedBook);

        OrderHistory orderHistory =  new OrderHistory(currentUser, selectedBook, selectedPayType, LocalDateTime.now());
        DataBase.orderHistoryList.add(orderHistory);

        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());
        userActivity.setCategory("");
        userActivity.setSelectedBook("");
        userActivity.setSelectedPayType("");

    }

    public void confirmLocation(User currentUser, Update update) {
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());

        if (update.getMessage().hasLocation()){
            helperMethods.sendMessage(currentUser, "Muvaffaqqiyatli sotib oldingiz! \n Tez orada kuryerlar sizga bog'lanadi!");
        }

        userActivity.setRound(1);
        helperMethods.sendMessage(currentUser, "Asosiy Menu");
        userActivity.setRound(2);
    }
}
