package uz.pdp.servis;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.MyBot;
import uz.pdp.model.Book;
import uz.pdp.model.PayType;
import uz.pdp.model.User;
import uz.pdp.model.UserActivity;
import uz.pdp.utils.DataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelperMethods {
    public void sendMessage(User currentUser, String text) {
        MyBot myBot = new MyBot();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

        try {
            myBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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

        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());
        int round = userActivity.getRound();

        switch (round) {
            case 0:
                KeyboardButton button = new KeyboardButton();
                button.setText("Raqamni jo'natish"); // share contact
                button.setRequestContact(true);
                row1.add(button);
                rowList.add(row1);
                break;
            case 1:
                row1.add("Kitob sotib olish");
                row2.add("Sotib olingan kitoblar");
                row2.add("Haridlar tarixi");
                rowList.add(row1);
                rowList.add(row2);
                break;
            case 2:
                Map<String, Book> bookMap = new HashMap<>();

                for (Book book : DataBase.bookList) {
                    bookMap.put(book.getCategory(), book);
                }

                for (String category : bookMap.keySet()) {
                    KeyboardRow row = new KeyboardRow();
                    row.add(category);
                    rowList.add(row);
                }
                break;
            case 3:
                for (int i = 0; i < DataBase.bookList.size(); i++) {
                    Book book = DataBase.bookList.get(i);
                    if (book.getCategory().equals(userActivity.getCategory())){
                        row1.add(String.valueOf(i+1));

                        if (i % 5 == 0){
                            rowList.add(row1);
                            row1 = new KeyboardRow();
                        }
                    }
                }
                rowList.add(row1);

                break;
            case 4:
                row1.add("Asosiy Menu");
                row1.add("Sotib Olish");
                rowList.add(row1);
                break;
            case 5:
                for (PayType payType : DataBase.payTypeList) {
                    KeyboardRow row = new KeyboardRow();
                    row.add(payType.getName());
                    rowList.add(row);
                }
                break;
            case 6:
                row1.add("Asosiy Menu");
                row1.add("Sotib Olish");
                rowList.add(row1);
                break;
            case 7:
                KeyboardButton button1 = new KeyboardButton();
                button1.setText("Lokatsiyani jo'natish"); // share Location
                button1.setRequestLocation(true);
                row1.add(button1);
                rowList.add(row1);
                break;

        }

        return keyboardMarkup;
    }


}
