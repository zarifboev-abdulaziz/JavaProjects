package uz.pdp.utils;

import jdk.internal.dynalink.linker.LinkerServices;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.model.Document;
import uz.pdp.model.User;
import uz.pdp.model.enums.Round;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class DataBase {

    public static List<User> userList = new ArrayList<>();

    public static List<Document> documentList = new ArrayList<>();


    public static Document getDocumentOfUser(User currentUser){
        for (Document document : documentList) {
            if (document.getUser().getId().equals(currentUser.getId())){
                return document;
            }
        }

        return null;
    }

    public static User getUserFromList(Update update){
        for (User user : userList) {
            if (user.getChatId().equals(update.getMessage().getChatId().toString())){
                return user;
            }
        }

        User newUser = new User();
        newUser.setFirstName(update.getMessage().getFrom().getFirstName());
        newUser.setLastName(update.getMessage().getFrom().getLastName());
        newUser.setUsername(update.getMessage().getFrom().getUserName());
        newUser.setChatId(update.getMessage().getChatId().toString());
        newUser.setRound(Round.NEUTRAL);

        userList.add(newUser);
        return newUser;

    }

}
