package uz.pdp;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.pdp.utils.DataBase;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DataBase.getDataFromPlaceHolder();

        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);

            api.registerBot(new MyBot());


        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        MyBot myBot = new MyBot();
        myBot.messageToUserService();

    }
}
