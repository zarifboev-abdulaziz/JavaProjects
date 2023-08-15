package uz.pdp.Service;

//Yunus Boliyev 14.12.2021, 16:54

import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.db.DataBase;
import uz.pdp.enums.BotState;
import uz.pdp.model.User;

public class BotService {

    public static User saveAndGetUser(Update update) {
        for (User user : DataBase.userList) {
            if (user.getUsername().equals(update.getMessage().getFrom().getUserName())) {
                return user;
            }
        }
        User user = new User();
        user.setChatId(update.getMessage().getChatId().toString());
        user.setUsername(update.getMessage().getFrom().getUserName());
        DataBase.userList.add(user);
        return user;
    }

    public static void updateUser(User user) {
        for (User user1 : DataBase.userList) {
            if (user.equals(user1)) {
                user1 = user;
            }
        }
    }
}
