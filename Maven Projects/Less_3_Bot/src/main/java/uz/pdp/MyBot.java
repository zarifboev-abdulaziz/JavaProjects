package uz.pdp;

import org.checkerframework.checker.units.qual.K;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



//Asilbek Fayzullayev 14.12.2021 10:14

public class MyBot extends TelegramLongPollingBot {


    @Override
    public String getBotToken() {
        return "5076204865:AAFANqtsphpAAG3PFph0VkvjdUeD6Z4j-Q8";
    }

    @Override
    public String getBotUsername() {
        return "@myFirstBot_b7_Bot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals("/start")) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText("Hello");
                    sendMessage.setChatId(message.getChatId());

                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    replyKeyboardMarkup.setResizeKeyboard(true);

                    List<KeyboardRow> keyboardRowList = new ArrayList<>();
                    KeyboardRow keyboardRow = new KeyboardRow();

                    KeyboardButton keyboardButton1 = new KeyboardButton();
                    KeyboardButton keyboardButton2 = new KeyboardButton();

                    keyboardButton1.setText("Location");
                    keyboardButton1.setRequestLocation(true);

                    keyboardButton2.setText("Share contact");
                    keyboardButton2.setRequestContact(true);

                    keyboardRow.add(keyboardButton1);
                    keyboardRow.add(keyboardButton2);

                    keyboardRowList.add(keyboardRow);

                    replyKeyboardMarkup.setKeyboard(keyboardRowList);

                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

       /* switch (massageUser.getText().toLowerCase()) {
                    case "/start":
                        sendMessage.setText("");

                        ReplyKeyboardMarkup replyKeyboardMarkup = getReplyKeyboardMarkup();
                        sendMessage.setReplyMarkup(replyKeyboardMarkup);


                        break;
                    case "hello🙋":
                        sendMessage.setText("Hi.How are you");
                        break;
                    default:
                        sendMessage.setText(massageUser.getText());
                        break;
                }

                execute(sendMessage);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    private ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();


        KeyboardRow row = new KeyboardRow();
        row.add("/start");

        row1.add("Hello🙋");
        row1.add("Button2");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Button3");
        row2.add("Button4");

        keyboardRowList.add(row);
        keyboardRowList.add(row1);
        keyboardRowList.add(row2);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }
}

  /*  private InlineKeyboardMarkup getInlineKeyboardMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonList = new ArrayList<>();

        // List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        //InlineKeyboardButton
        // rowList.add(new InlineKeyboardButton("Inline button1"));

        // rowList.add(keyboardButtonList);

        //  inlineKeyboardMarkup.setKeyboard(rowList);
    }
}*/











































