package uz.pdp.bot.customerServiceForBot.seeClothesForBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.bot.MyBot;
import uz.pdp.bot.customerServiceForBot.UserActivity;
import uz.pdp.model.Cloth;
import uz.pdp.model.OrderItem;
import uz.pdp.model.abs.User;
import uz.pdp.model.enums.Stage;
import uz.pdp.utils.Db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SeeClothesForBot {

    public void responseToCallBackQuery(User currentUser, CallbackQuery callbackQuery){

        switch (currentUser.getStage()) {
            case SEE_CLOTHES:
                seeClothesMethod(currentUser, callbackQuery);
                break;
            case CHOOSE_QUANTITY:
                chooseQuantityMethod(currentUser, callbackQuery);
                break;
        }



    }

    private void chooseQuantityMethod(User currentUser, CallbackQuery callbackQuery) {
        OrderItem orderItem = currentUser.getMyCart().get(currentUser.getMyCart().size() - 1);
        UserActivity userActivity = Db.userActivityMap.get(currentUser.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(currentUser.getChatId());
        MyBot myBot = new MyBot();
        String data = callbackQuery.getData();

        switch (data){
            case "➖":
                userActivity.setCurrentClothQuantity(userActivity.getCurrentClothQuantity() - 1);
                editQuantityMsg(currentUser, "Select Quantity");

                break;
            case "currentQuantity":
                return;
            case "➕":
                userActivity.setCurrentClothQuantity(userActivity.getCurrentClothQuantity() + 1);
                editQuantityMsg(currentUser, "Select Quantity");
                break;

            case "Add to cart":
                currentUser.setStage(Stage.CUSTOMER_MENU);
                editQuantityMsg(currentUser, "Successfully added to cart!");
                currentUser.setStage(Stage.CHOOSE_QUANTITY);

                orderItem.setQuantity(userActivity.getCurrentClothQuantity());

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                deleteMessage.setMessageId(userActivity.getQuantityMsgId());

                try {
                    myBot.execute(deleteMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                userActivity.setCurrentClothQuantity(1);
                currentUser.setStage(Stage.SEE_CLOTHES);

                break;
        }
    }

    public  void editQuantityMsg(User currentUser, String text){
        UserActivity userActivity = Db.userActivityMap.get(currentUser.getChatId());
        MyBot myBot = new MyBot();
        OrderItem orderItem = currentUser.getMyCart().get(currentUser.getMyCart().size() - 1);

        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setChatId(currentUser.getChatId());
        editMessageReplyMarkup.setMessageId(userActivity.getQuantityMsgId());
        editMessageReplyMarkup.setReplyMarkup(getInlineMarkup(currentUser));

        try {
            myBot.execute(editMessageReplyMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void seeClothesMethod(User currentUser, CallbackQuery callbackQuery) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(currentUser.getChatId());
        editMessageText.setMessageId(currentUser.getCurrentMessageId());

        MyBot myBot = new MyBot();
        String data = callbackQuery.getData();
        UserActivity userActivity = Db.userActivityMap.get(currentUser.getChatId());

        switch (data){
            case "cancel":
                OrderItem orderItem = currentUser.getMyCart().get(currentUser.getMyCart().size() - 1);
                currentUser.getMyCart().remove(orderItem);

                break;
            case "Previous": {

                currentUser.setClothPage(currentUser.getClothPage() - 10);

                String clothesInfo = "Choose one Cloth from the list below!\n";

                for (int i = currentUser.getClothPage(); i < currentUser.getClothPage() + 10 && i < Db.clothList.size(); i++) {
                    Cloth cloth = Db.clothList.get(i);
                    clothesInfo += "" + (i+1) + ". " + cloth.getName() + "\n";
                }

                editMessageText.setText(clothesInfo);
                editMessageText.setReplyMarkup(getInlineMarkup(currentUser));

                try {
                    myBot.execute(editMessageText);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }break;

            case "Main Menu": {
                currentUser.setStage(Stage.CUSTOMER_MENU);
                sendMessage(currentUser, "Choose one option!!! ", true);

            } break;

            case "Next": {

                currentUser.setClothPage(currentUser.getClothPage() + 10);

                String clothesInfo = "Choose one Cloth from the list below!\n";

                for (int i = currentUser.getClothPage(); i < currentUser.getClothPage() + 10 && i < Db.clothList.size(); i++) {
                    Cloth cloth = Db.clothList.get(i);
                    clothesInfo += "" + (i+1) + ". " + cloth.getName() + "\n";
                }

                editMessageText.setText(clothesInfo);
                editMessageText.setReplyMarkup(getInlineMarkup(currentUser));

                try {
                    myBot.execute(editMessageText);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }break;

            default:{
                currentUser.setStage(Stage.CHOOSE_QUANTITY);

                Cloth cloth = Db.clothList.get(Integer.parseInt(data));

                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(currentUser.getChatId());
                sendPhoto.setPhoto(new InputFile(new File(cloth.getImgUrl())));

                String clothInfo ="Name: " + cloth.getName() + "\n" +
                        "Price: " + cloth.getPrice() + "\n" +
                        "Discount: " + cloth.getDiscount() + "\n" +
                        "Size: " + cloth.getSize() + "\n" +
                        "Color: " + cloth.getColor().getName() + "\n";

                sendPhoto.setCaption(clothInfo);
                sendPhoto.setReplyMarkup(getInlineMarkup(currentUser));

                try {
                    userActivity.setQuantityMsgId(myBot.execute(sendPhoto).getMessageId());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                OrderItem currentOrderItem = new OrderItem();
                currentOrderItem.setCloth(cloth);
                currentUser.getMyCart().add(currentOrderItem);

            }break;
        }

    }

    public void seeClothesProcess(User currentUser, Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        UserActivity userActivity = Db.userActivityMap.get(currentUser.getChatId());
        MyBot myBot = new MyBot();
        String text = message.getText();


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

    public InlineKeyboardMarkup getInlineMarkup(User currentUser){
        UserActivity userActivity = Db.userActivityMap.get(currentUser.getChatId());
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        //ROWS
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        //BUTTONS
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        InlineKeyboardButton button4 = new InlineKeyboardButton();

        switch (currentUser.getStage()){
            case SEE_CLOTHES:

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

                button1.setText("Previous");
                button1.setCallbackData("Previous");

                button2.setText("Main Menu");
                button2.setCallbackData("Main Menu");

                button3.setText("Next");
                button3.setCallbackData("Next");

                if (currentUser.getClothPage() == 0){
                    row3.add(button2);
                    row3.add(button3);
                } else if (currentUser.getClothPage() + 5 >= Db.clothList.size()){
                    row3.add(button1);
                    row3.add(button2);
                } else {
                    row3.add(button1);
                    row3.add(button2);
                    row3.add(button3);
                }

                rowList.add(row3);
                break;
            case CHOOSE_QUANTITY:
                button1.setText("➖");
                button1.setCallbackData("➖");

                button2.setText(String.valueOf(userActivity.getCurrentClothQuantity()));
                button2.setCallbackData("currentQuantity");

                button3.setText("➕");
                button3.setCallbackData("➕");

                button4.setText("Add to cart");
                button4.setCallbackData("Add to cart");

                if (userActivity.getCurrentClothQuantity() == 1){
                    row1.add(button2);
                    row1.add(button3);
                    row2.add(button4);
                } else {
                    row1.add(button1);
                    row1.add(button2);
                    row1.add(button3);
                    row2.add(button4);
                }


                rowList.add(row1);
                rowList.add(row2);
                break;

            case CUSTOMER_MENU:
                button1.setText("Cancel");
                button1.setCallbackData("cancel");

                row1.add(button1);
                rowList.add(row1);
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
            case SEE_CLOTHES:
                row1.add("Main Menu");
                row1.add("Add to cart!");
                rowList.add(row1);
                break;
        }

        return keyboardMarkup;
    }


}
