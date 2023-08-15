package uz.pdp.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.bot.customerServiceForBot.CustomerServiceForBot;
import uz.pdp.model.abs.User;
import uz.pdp.utils.Db;

public class MyBot extends TelegramLongPollingBot {


    public static final String botUsername = "@Find_Passport_Uz_Bot";
    public static final String botToken = "5050759706:AAE64RPPK5ybZ-vStZv5_cJfL2AI7wOd1iw";


    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    CustomerServiceForBot customerService = new CustomerServiceForBot();

    @Override
    public void onUpdateReceived(Update update) {
        User currentUser = Db.getUserFromList(update);

        switch (currentUser.getRole()) {
            case CUSTOMER:
                customerService.customerMenu(update, currentUser);
                break;
            case ADMIN:
                //adminService.adminMenu(update, currentUser);
                break;
        }


    }


}
