package uz.pdp;

import com.sun.xml.internal.ws.resources.SenderMessages;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.model.User;
import uz.pdp.model.UserActivity;
import uz.pdp.servis.HelperMethods;
import uz.pdp.servis.RoundMethods;
import uz.pdp.utils.DataBase;

import java.util.ArrayList;
import java.util.List;

import static uz.pdp.utils.Constants.botToken;
import static uz.pdp.utils.Constants.botUsername;


public class MyBot extends TelegramLongPollingBot {
    RoundMethods roundMethods = new RoundMethods();
    HelperMethods helperMethods = new HelperMethods();


    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        User currentUser = DataBase.getUserFromList(update);
        UserActivity userActivity = DataBase.userActivityMap.get(currentUser.getChatId());

        if (currentUser.getPhoneNumber() != null && update.getMessage().hasText() && update.getMessage().getText().equals("/start")){
            userActivity.setRound(1);
            helperMethods.sendMessage(currentUser, "Asosiy Menu");
            userActivity.setRound(2);
        }


        switch (userActivity.getRound()){
            case 0:
                roundMethods.registerProcess(currentUser, update);
                break;
            case 1:
                roundMethods.confirmContact(currentUser, update);
                break;
            case 2:
                roundMethods.selectFromMainMenu(currentUser, update);
                break;
            case 3:
                roundMethods.selectingCategory(currentUser, update);
                break;
            case 4:
                roundMethods.selectingBook(currentUser, update);
                break;
            case 5:
                roundMethods.deciding(currentUser, update); //qaror qabul qilyabdi, sotib olaymi yoki yuqmi
                break;
            case 6:
                roundMethods.selectingPayType(currentUser, update);
                break;
            case 7:
                roundMethods.deciding2(currentUser, update); //qaror qabul qilyabdi, sotib olaymi yoki yuqmi pul to'lash jarayoni!
                break;
            case 8:
                roundMethods.confirmLocation(currentUser, update); //qaror qabul qilyabdi, sotib olaymi yoki yuqmi pul to'lash jarayoni!
                break;
        }


    }




}
