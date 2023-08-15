package uz.pdp;


import uz.pdp.model.Passport;
import uz.pdp.model.State;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public static List<Passport> passportList = new ArrayList<>();

    public static Passport getOrCreatePassportByChatId(String chatID){
        for (Passport passport : passportList) {
            if (passport.getChatId().equals(chatID)){
                return passport;
            }
        }

        Passport passport = new Passport();
        passport.setChatId(chatID);
        passportList.add(passport);
        return passport;
    }
}
