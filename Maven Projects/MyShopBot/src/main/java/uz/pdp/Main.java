package uz.pdp;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.pdp.bot.MyBot;
import uz.pdp.service.MainServiceImpl;

import static uz.pdp.utils.DataFromJson.getAllDataFromJson;

public class Main {

    public static void main(String[] args) {

        getAllDataFromJson();

        uz.pdp.bot.Main main = new uz.pdp.bot.Main();
        main.startBot();


        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);

            api.registerBot(new MyBot());


        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


        MainServiceImpl mainService = new MainServiceImpl();

        mainService.showMenu();

    }
}
