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
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
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

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyCartServiceForBot {


    public void responseToCallBackQuery(User currentUser, CallbackQuery callbackQuery){

        switch (currentUser.getStage()) {
            case SHOW_USER_CLOTHES: break;
            case CHECK_OUT:
                responseToCheckOut(currentUser, callbackQuery);
                break;
            case CLEAR_CART:break;
            case REMOVE_ITEM:
                responseToRemoveItem(currentUser, callbackQuery);
                break;
        }

    }

    private void responseToRemoveItem(User currentUser, CallbackQuery callbackQuery) {
        String data = callbackQuery.getData();
        OrderItem orderItem = currentUser.getMyCart().get(Integer.parseInt(data));

        currentUser.getMyCart().remove(orderItem);

        sendMessage(currentUser, "Item Successfully Removed!!!");

        currentUser.setStage(Stage.MY_CART_MENU);
        sendMessage(currentUser, "Choose one option!!!", true);
    }

    private void responseToCheckOut(User currentUser, CallbackQuery callbackQuery) {
        UserActivity userActivity = Db.userActivityMap.get(currentUser.getChatId());
        MyBot myBot = new MyBot();
        String data = callbackQuery.getData();

        PayType payType = Db.payTypeList.get(Integer.parseInt(data));
        userActivity.setPayType(payType);

        String transactionInfo = "Will you confirm this transaction?\n";

        double totalPrice = 0;
        for (OrderItem orderItem : currentUser.getMyCart()) {
            totalPrice += (orderItem.getCloth().getPrice()) * (orderItem.getQuantity());
            transactionInfo += "Name: " + orderItem.getCloth().getName() + ", Quantity: " + orderItem.getQuantity() + "\n";
        }
        double finalPrice = totalPrice * (100 + payType.getCommissionFee())/100;

        transactionInfo += "Commission Fee: " + (totalPrice * payType.getCommissionFee()/100) + "\n" +
        "Total Price: " + finalPrice + "\n";

        currentUser.setStage(Stage.SHOW_USER_CLOTHES);
        sendMessage(currentUser, transactionInfo, true);
        currentUser.setStage(Stage.CHECK_OUT);

    }

    public void myCartMenu(User currentUser, Message message){
        String text = "";
        if (message.hasText()){
             text = message.getText();
        }


        switch (text){
            case "See clothes in my cart":
                currentUser.setStage(Stage.SHOW_USER_CLOTHES);
                break;
            case "Check out":
                currentUser.setStage(Stage.CHECK_OUT);
                break;
            case "Remove Item from cart":
                currentUser.setStage(Stage.REMOVE_ITEM);
                break;
            case "clear cart":
                currentUser.setStage(Stage.CLEAR_CART);
                break;
            case "Main Menu":
                currentUser.setStage(Stage.CUSTOMER_MENU);
                sendMessage(currentUser, "Choose one option below!", true);

                break;

        }

        switch (currentUser.getStage()) {
            case SHOW_USER_CLOTHES:
                showClothesInUserCart(currentUser);
                break;
            case CHECK_OUT:
                checkOutProcess(currentUser, message);
                break;
            case REMOVE_ITEM:
                removeItemProcess(currentUser, message);
                break;
            case CLEAR_CART:
                clearCartProcess(currentUser);
                break;

        }

    }

    private void clearCartProcess(User currentUser) {
        currentUser.getMyCart().removeAll(currentUser.getMyCart());
        sendMessage(currentUser, "Cart is cleared!!!");

        currentUser.setStage(Stage.MY_CART_MENU);
        sendMessage(currentUser, "Choose one option!!!", true);
    }

    private void removeItemProcess(User currentUser, Message message) {
        if (currentUser.getMyCart().size() == 0){
            currentUser.setStage(Stage.MY_CART_MENU);
            sendMessage(currentUser, "Cart is empty!!!\nChoose one option!!!", true);
            return;
        }

        sendMessage(currentUser, "Choose item below to remove!", false);

    }

    private void checkOutProcess(User currentUser, Message message) {
        CheckOutProcess checkOutProcess = new CheckOutProcess();
        if (message.hasContact()){
            Contact contact = message.getContact();
            currentUser.setPhoneNumber(contact.getPhoneNumber());

            if (currentUser.getMyCart().size() == 0){
                currentUser.setStage(Stage.MY_CART_MENU);
                sendMessage(currentUser, "Cart is empty!!!\nChoose one option!!!", true);
                return;
            }
            sendMessage(currentUser, "Choose one of the payment types!", false);

        }

        if (currentUser.getPhoneNumber() == null){
            sendMessage(currentUser, "In order to check out please share your contact first!", true);
            return;
        }

        if (currentUser.getMyCart().size() == 0){
            currentUser.setStage(Stage.MY_CART_MENU);
            sendMessage(currentUser, "Cart is empty!!!\nChoose one option!!!", true);
            return;
        }
        
        if (message.getText().equals("Check out")){
            sendMessage(currentUser, "Choose one of the payment types!", false);
        }

        if (message.getText().equals("Reject")){
            currentUser.setStage(Stage.MY_CART_MENU);
            sendMessage(currentUser, "Check out Rejected!!!", true);
        }

        if (message.getText().equals("Confirm")){
            checkOutProcess.transactionProcess(currentUser);
        }

    }

    private void showClothesInUserCart(User currentUser) {
        MyBot myBot = new MyBot();
        if (currentUser.getMyCart().size() == 0){
            currentUser.setStage(Stage.MY_CART_MENU);
            sendMessage(currentUser, "Cart is empty!!!\nChoose one option!!!", true);
            return;
        }

        for (int i = 0; i < currentUser.getMyCart().size(); i++) {
            OrderItem orderItem = currentUser.getMyCart().get(i);

            String clothInfo ="Name: " + orderItem.getCloth().getName() + "\n" +
                    "Price: " + orderItem.getCloth().getPrice() + "\n" +
                    "Discount: " + orderItem.getCloth().getDiscount() + "\n" +
                    "Size: " + orderItem.getCloth().getSize() + "\n" +
                    "Color: " + orderItem.getCloth().getColor() + "\n" +
                    "Quantity: "  + orderItem.getQuantity() + "\n";

            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(currentUser.getChatId());
            sendPhoto.setPhoto(new InputFile(new File(orderItem.getCloth().getImgUrl())));
            sendPhoto.setCaption(clothInfo);

            try {
                myBot.execute(sendPhoto);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        currentUser.setStage(Stage.MY_CART_MENU);
        sendMessage(currentUser, "Choose one option!!!", true);

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
    private void sendMessage(User currentUser, String text) {
        MyBot myBot = new MyBot();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        sendMessage.setText(text);

        try {
            myBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
                }   break;

            case REMOVE_ITEM:
                for (int i = 0; i < currentUser.getMyCart().size(); i++) {
                    OrderItem orderItem = currentUser.getMyCart().get(i);

                    List<InlineKeyboardButton> orderItemRow = new ArrayList<>();
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText("Name:  " + orderItem.getCloth().getName() + ", Quantity: " + orderItem.getQuantity() +
                            ", Price: " + orderItem.getCloth().getPrice());
                    button.setCallbackData(String.valueOf(i));

                    orderItemRow.add(button);
                    rowList.add(orderItemRow);
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

                // this case (below) is helping for checkout!!!!
            case SHOW_USER_CLOTHES:
                row1.add("Reject");
                row1.add("Confirm");
                rowList.add(row1);
                break;
        }

        return keyboardMarkup;
    }


}
