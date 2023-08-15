package uz.pdp.bot.customerServiceForBot.myCartService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.bot.MyBot;
import uz.pdp.bot.customerServiceForBot.UserActivity;
import uz.pdp.model.OrderHistory;
import uz.pdp.model.OrderItem;
import uz.pdp.model.PayType;
import uz.pdp.model.abs.User;
import uz.pdp.model.enums.Stage;
import uz.pdp.utils.Db;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheckOutProcess {
   public void transactionProcess(User currentUser){
      UserActivity userActivity = Db.userActivityMap.get(currentUser.getChatId());
      MyBot myBot = new MyBot();
      PayType payType = userActivity.getPayType();

      double totalPrice = 0;
      for (OrderItem orderItem : currentUser.getMyCart()) {
         totalPrice += (orderItem.getCloth().getPrice()) * (orderItem.getQuantity());
      }
      double finalPrice = totalPrice * (100 + payType.getCommissionFee())/100;

      if (currentUser.getBalance() >= finalPrice) {

         currentUser.setBalance(currentUser.getBalance() - finalPrice);
         Db.admin.setBalance(Db.admin.getBalance() + totalPrice);

         String text = "Successfully bought!!!\n" + "Your balance => " + currentUser.getBalance() + "\n";

         File file = downloadPdf_check(currentUser, payType);
         SendDocument sendDocument = new SendDocument();
         sendDocument.setChatId(currentUser.getChatId());
         sendDocument.setDocument(new InputFile(file));
         sendDocument.setCaption(text);

         try {
            myBot.execute(sendDocument);
         } catch (TelegramApiException e) {
            e.printStackTrace();
         }

         currentUser.setStage(Stage.MY_CART_MENU);
         sendMessage(currentUser, "Choose one option!!!", true);

      } else {
         currentUser.setStage(Stage.MY_CART_MENU);
         sendMessage(currentUser, "You don't have enough money!!!", true);
         return;
      }

      currentUser.getMyCart().removeAll(currentUser.getMyCart());
      userActivity.setPayType(null);
      // TODO: 21.12.2021 Record to History and Refactor

   }



   private void sendMessage(User currentUser, String text, boolean isReplyMarkup) {
      MyBot myBot = new MyBot();
      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(currentUser.getChatId());
      sendMessage.setText(text);

      if (isReplyMarkup){
         sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));
      } else {
         sendMessage.setReplyMarkup(getInlineMarkup(currentUser));
      }

      try {
         myBot.execute(sendMessage);
      } catch (TelegramApiException e) {
         e.printStackTrace();
      }
   }

   public File downloadPdf_check(User customer, PayType selectedPayType) {
      File file = new File("src/main/resources/test.pdf");
      try (PdfWriter writer = new PdfWriter("src/main/resources/test.pdf")) {
         PdfDocument pdfDocument = new PdfDocument(writer);

         pdfDocument.addNewPage();
         Document document = new Document(pdfDocument);
         Paragraph paragraph = new Paragraph(customer.getFullName()+" 's check paper");
         document.add(paragraph);
         float[] pointColumnWidth = {150F, 150F, 150F,150F, 150F, 150F,150F};
         Table table = new Table(pointColumnWidth);
         table.addCell("T/R");
         table.addCell("Customer's name");
         table.addCell("PayType");
         table.addCell("ClothName");
         table.addCell("Cloth price");
         table.addCell("Cloth quantity");
         table.addCell("Purchase time");
         Gson gson = new Gson();

         Reader reader = new FileReader("src/main/resources/jsons/orderHistoryList.json");
         // Transaction[] transactions = gson.fromJson(reader, Transaction[].class);

         List<OrderHistory> orderHistoryList = gson.fromJson(reader, new TypeToken<List<OrderHistory>>(){}.getType());

         for (int i = 0; i < customer.getMyCart().size(); i++) {
            OrderItem orderItem = customer.getMyCart().get(i);

            table.addCell(String.valueOf(i+1));


            table.addCell(String.valueOf(customer.getFullName()));
            table.addCell(selectedPayType.getName());
            table.addCell(orderItem.getCloth().getName());
            table.addCell(String.valueOf(orderItem.getCloth().getPrice()));
            table.addCell(String.valueOf(orderItem.getQuantity()));
            table.addCell(String.valueOf(LocalDateTime.now()));
         }

         System.out.println("Loading...");
         System.out.println("Success!!!!");

         document.add(table);
         document.close();

      } catch (IOException e) {
         e.printStackTrace();
      }

      return file;

   }

   public InlineKeyboardMarkup getInlineMarkup(User currentUser){
      InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

      List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

      switch (currentUser.getStage()){
         case SEE_CLOTHES:
            List<InlineKeyboardButton> row1 = new ArrayList<>();
            List<InlineKeyboardButton> row2 = new ArrayList<>();

            for (int i = currentUser.getClothPage(); i < currentUser.getClothPage() + 5 && i < Db.clothList.size(); i++) {
               InlineKeyboardButton button = new InlineKeyboardButton();

               button.setText(String.valueOf(i+1));
               button.setCallbackData(String.valueOf(i));
               row1.add(button);
            }
            rowList.add(row1);

            for (int i = currentUser.getClothPage() + 5; i < currentUser.getClothPage() + 10 && i < Db.clothList.size(); i++) {
               InlineKeyboardButton button = new InlineKeyboardButton();

               button.setText(String.valueOf(i+1));
               button.setCallbackData(String.valueOf(i));
               row2.add(button);
            }
            rowList.add(row2);

            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button1 = new InlineKeyboardButton();
            InlineKeyboardButton button2 = new InlineKeyboardButton();
            InlineKeyboardButton button3 = new InlineKeyboardButton();

            button1.setText("Previous");
            button1.setCallbackData("Previous");

            button2.setText("Main Menu");
            button2.setCallbackData("Main Menu");

            button3.setText("Next");
            button3.setCallbackData("Next");

            if (currentUser.getClothPage() == 0){
               row.add(button2);
               row.add(button3);
            } else if (currentUser.getClothPage() >= Db.clothList.size() - 10){
               row.add(button1);
               row.add(button2);
            } else {
               row.add(button1);
               row.add(button2);
               row.add(button3);
            }

            rowList.add(row);
            break;

         case CHECK_OUT:

            for (int i = 0; i < Db.payTypeList.size(); i++) {
               PayType payType = Db.payTypeList.get(i);

               List<InlineKeyboardButton> payTypeRow = new ArrayList<>();
               InlineKeyboardButton button = new InlineKeyboardButton();
               button.setText("Name:  " + payType.getName() + ", Commission Fee: " + payType.getCommissionFee() + "%");
               button.setCallbackData(String.valueOf(i));

               payTypeRow.add(button);
               rowList.add(payTypeRow);
            }

            break;

      }
      inlineKeyboardMarkup.setKeyboard(rowList);

      return inlineKeyboardMarkup;
   }

   private ReplyKeyboard getReplyKeyBoard(User currentUser) {

      ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
      keyboardMarkup.setResizeKeyboard(true);
      keyboardMarkup.setOneTimeKeyboard(true);

      List<KeyboardRow> rowList = new ArrayList<>();
      keyboardMarkup.setKeyboard(rowList);
      KeyboardRow row1 = new KeyboardRow();
      KeyboardRow row2 = new KeyboardRow();
      KeyboardRow rowN = new KeyboardRow();

      Stage state = currentUser.getStage();
//1=> See clothes, 2=> Filter Clothes 3=> My cart, 4=> Login, 5=> Register, 0=> Exit
      switch (state) {
         case CUSTOMER_MENU:
            row1.add("See clothes");
            row1.add("My Cart");
            rowList.add(row1);
            break;
         case MY_CART_MENU:
            row1.add("See clothes in my cart");
            row1.add("Check out");
            row2.add("Remove Item from cart");
            row2.add("clear cart");
            rowN.add("Main Menu");

            rowList.add(row1);
            rowList.add(row2);
            rowList.add(rowN);
            break;
         case CHECK_OUT:
            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setText("Share your contact");
            keyboardButton.setRequestContact(true);
            row1.add(keyboardButton);
            rowList.add(row1);
            break;
      }

      return keyboardMarkup;
   }

}
