package uz.pdp;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;



//Asilbek Fayzullayev 14.12.2021 10:13

public class Main {
    public static void main(String[] args) {


        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(new MyBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

}
