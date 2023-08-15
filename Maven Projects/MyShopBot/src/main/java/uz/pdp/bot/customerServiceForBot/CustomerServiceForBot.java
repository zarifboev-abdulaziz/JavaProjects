package uz.pdp.bot.customerServiceForBot;

import org.bouncycastle.util.test.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.bot.MyBot;
import uz.pdp.bot.customerServiceForBot.myCartService.MyCartServiceForBot;
import uz.pdp.bot.customerServiceForBot.seeClothesForBot.SeeClothesForBot;
import uz.pdp.model.Cloth;
import uz.pdp.model.abs.User;
import uz.pdp.model.enums.Stage;
import uz.pdp.utils.Db;

import javax.security.auth.Subject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceForBot {
    MyCartServiceForBot cartService = new MyCartServiceForBot();
    SeeClothesForBot seeClothesService = new SeeClothesForBot();

    public void customerMenu(Update update, User currentUser){

        if (update.hasMessage()){
            process(currentUser, update);
        } else if (update.hasCallbackQuery()){

            switch (currentUser.getStage()) {
                case REMOVE_ITEM:
                case CLEAR_CART:
                case CHECK_OUT:
                case SHOW_USER_CLOTHES:
                case MY_CART_MENU:
                    cartService.responseToCallBackQuery(currentUser, update.getCallbackQuery());
                    break;

                case SEE_CLOTHES:
                case CHOOSE_QUANTITY:
                    seeClothesService.responseToCallBackQuery(currentUser, update.getCallbackQuery());
                    break;
            }

        }

    }

    private void process(User currentUser,Update update) {
        String msg = "";

        if (update.hasMessage() && update.getMessage().hasText()){
            msg = update.getMessage().getText();
        }

        if (msg.equals("/start")){
            String text = "Assalomu Alaykum, Botga hush kelibsiz";
            currentUser.setStage(Stage.CUSTOMER_MENU);
            sendMessage(currentUser, text, true);
            return;
        }

        switch (currentUser.getStage()) {
            case CUSTOMER_MENU:
                customerMenuProcess(currentUser, update.getMessage());
                break;

            case REMOVE_ITEM:
            case CLEAR_CART:
            case CHECK_OUT:
            case SHOW_USER_CLOTHES:
            case MY_CART_MENU:
                cartService.myCartMenu(currentUser, update.getMessage());
                break;
            case SEE_CLOTHES:
                seeClothesService.seeClothesProcess(currentUser, update.getMessage());
                break;

        }
    }

    private void customerMenuProcess(User currentUser, Message message) {
        MyBot myBot = new MyBot();
        String msg = message.getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());


        if (msg.equals("See clothes")){
            currentUser.setStage(Stage.SEE_CLOTHES);

            String clothesInfo = "Choose one Cloth from the list below!\n";

            for (int i = 0; i < currentUser.getClothPage() + 10 && i < Db.clothList.size(); i++) {
                Cloth cloth = Db.clothList.get(i);
                clothesInfo += "" + (i+1) + ". " + cloth.getName() + "\n";
            }

            sendMessage.setText(clothesInfo);
            sendMessage.setReplyMarkup(getInlineMarkup(currentUser));

            try {
                currentUser.setCurrentMessageId(myBot.execute(sendMessage).getMessageId());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else if (msg.equals("My Cart")){
            currentUser.setStage(Stage.MY_CART_MENU);
            sendMessage(currentUser, "Choose one option!", true);

        }

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
        }

        return keyboardMarkup;
    }


}
