package uz.pdp.utils;

import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.model.*;
import uz.pdp.model.enums.Type;


import java.util.*;


public class DataBase {

    public static List<User> userList = new ArrayList<>();
    public static Map<String, UserActivity> userActivityMap = new HashMap<>();
    public static List<OrderHistory> orderHistoryList = new ArrayList<>();

    public static List<PayType> payTypeList = new ArrayList<>(Arrays.asList(
            new PayType("Naqd", 0),
            new PayType("CLICK", 0.5),
            new PayType("Pay Me", 1)
    ));


    public static List<Book> bookList = new ArrayList<>(Arrays.asList(
            new Book("O'gay Ona", "Ahmad Lutfiy Qozonchi", 10000, "Jahon Adabiyoti", Type.EBook, "src/main/resources/Ahmad Lutfiy Qozonchi. O'gay ona (roman).pdf"),
            new Book("GodFather", "Mario Puzo", 15000, "Jahon Adabiyoti", Type.EBook, "src/main/resources/The Godfather - Mario Puzo.pdf"),
            new Book("O'tgan kunlar", "Abdulla Qodiriy", 5000, "O'zbek Adabiyoti", Type.AudioBook, "src/main/resources/001 - O'TKAN KUNLAR.mp3"),
            new Book("Mehrobdan Chayon", "Abdulla Qodiriy", 35000, "O'zbek Adabiyoti", Type.PrintedBook, " "),
            new Book("Kapitan Qizi", "Aleksandr Pushkin", 7000, "Bolalar Adabiyoti", Type.EBook, "src/main/resources/Aleksandr Pushkin. Kapitan qizi (qissa).pdf"),
            new Book("Sariq Devni Minib", "Xudoyberdi To'xtaboyev", 40000, "Bolalar Adabiyoti", Type.PrintedBook, " ")
    ));


    public static User getUserFromList(Update update){
        if(update.hasMessage()){
            for (User user : userList) {
                if (user.getChatId().equals(update.getMessage().getChatId().toString())){
                    return user;
                }
            }
        } else if (update.hasCallbackQuery()){
            for (User user : userList) {
                if (user.getChatId().equals(update.getCallbackQuery().getMessage().getChatId().toString())){
                    return user;
                }
            }
        }


        User newUser = new User();
        newUser.setFirstName(update.getMessage().getFrom().getFirstName());
        newUser.setLastName(update.getMessage().getFrom().getLastName());
        newUser.setChatId(update.getMessage().getChatId().toString());

        DataBase.userActivityMap.put(newUser.getChatId(), new UserActivity());

        userList.add(newUser);
        return newUser;
    }

}
