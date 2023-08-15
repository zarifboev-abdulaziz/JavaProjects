package uz.pdp;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.model.Passport;
import uz.pdp.model.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyBot extends TelegramLongPollingBot {
    @Override
    public String getBotToken() {
        return "5050759706:AAE64RPPK5ybZ-vStZv5_cJfL2AI7wOd1iw";
    }

    @Override
    public String getBotUsername() {
        return "@Find_Passport_Uz_Bot";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());


        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);


        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton keyboardButton1 = new KeyboardButton();
        KeyboardButton keyboardButton2 = new KeyboardButton();

        keyboardButton1.setText("Search Passport");
        keyboardButton2.setText("Add Passport");

        row1.add(keyboardButton1);
        row1.add(keyboardButton2);

        keyboardRowList.add(row1);
        
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        if (update.hasMessage() && update.getMessage().hasText()){
            process(update.getMessage());
        }

        execute(sendMessage);

    }

    public void process(Message message){
        Passport passport = DataBase.getOrCreatePassportByChatId(message.getChatId().toString());

        String msg = message.getText();
        if (msg.equals("Add Passport")){
        passport.setState(State.ADDPASSPORT);

        }else if (msg.equals("Search Passport")){
            passport.setState(State.SEARCHPASSPORT);
        }

        switch (passport.getState()){
            case ADDPASSPORT:
                processAddPassport(passport, msg);
                break;
            case SEARCHPASSPORT:
                break;
        }

    }

    @SneakyThrows
    private void processAddPassport(Passport passport, String msg)  {
        if (msg.equals("Add Passport")){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Please Enter First Name: ");
            sendMessage.setChatId(passport.getChatId());
            execute(sendMessage);
            return;
        }

        if (passport.getFirstname() == null){
            passport.setFirstname(msg);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Please Enter Surname: ");
            sendMessage.setChatId(passport.getChatId());
            execute(sendMessage);
            return;
        }

        if (passport.getSurname() == null){
            passport.setSurname(msg);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Please Enter SerialNumber: ");
            sendMessage.setChatId(passport.getChatId());
            execute(sendMessage);
            return;
        }

        if (passport.getSerialNumber() == null){
            passport.setSerialNumber(msg);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Please Enter your phone Number to contact: ");
            sendMessage.setChatId(passport.getChatId());
            execute(sendMessage);
            return;
        }

        if (passport.getContactNumber() == null){
            passport.setContactNumber(msg);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Successfully Added!!!");
            sendMessage.setChatId(passport.getChatId());
            execute(sendMessage);
            passport.setState(State.GENERAL);
        }

    }

}
