package uz.pdp.utils;

import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.bot.customerServiceForBot.UserActivity;
import uz.pdp.model.*;
import uz.pdp.model.abs.User;
import uz.pdp.model.enums.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Db {

    public static Map<String, UserActivity> userActivityMap= new HashMap<>();

    public static User admin = new User("admin", "a", "1", 0, Role.ADMIN);

    public static List<OrderItem> temporaryOrder = new ArrayList<>();

    public static List<User> userList = new ArrayList<>(
//            Arrays.asList(
//            new Admin("admin", "a", "1", 0),
//            new Customer("Asadbek Halimjonov", "b", "1", 1_000_000, new ArrayList<>())
//    )
    );


    public static List<Color> colorList = new ArrayList<>(
//            Arrays.asList(
//            new Color("Black"),
//            new Color("Blue"),
//            new Color("Dark blue"),
//            new Color("Red")
//    )
    );
    //
    public static List<Cloth> clothList = new ArrayList<>(
//            Arrays.asList(
//            new Cloth("T-Shirt", colorList.get(0), Size.L, 30_000, 0),
//            new Cloth("T-Shirt (red)", colorList.get(0), Size.L, 60_000, 5),
//            new Cloth("Cap", colorList.get(0), Size.XL, 10000, 0)
//    )
    );


    public static List<StoreItem> store = new ArrayList<>(
//            Arrays.asList(
//            new StoreItem(clothList.get(0), 15),
//            new StoreItem(clothList.get(1), 4),
//            new StoreItem(clothList.get(2), 10)
//    )
    );


    public static List<PayType> payTypeList = new ArrayList<>(
//            Arrays.asList(
//            new PayType("Payme", 1),
//            new PayType("Click", 0.5)
//    )
    );

    public static List<OrderHistory> orderHistoryList = new ArrayList<>(
//            Arrays.asList(
//            new OrderHistory(
//                    (Customer) userList.get(1),
//                    new ArrayList<OrderItem>(Arrays.asList(
//                            new OrderItem(clothList.get(0), 1),
//                            new OrderItem(clothList.get(2), 2)
//                    )),
//                    50_000,
//                    250,
//                    payTypeList.get(1)
//            )
//
//
//    )
    );


    public static User getUserFromList(Update update){

        if(update.hasMessage()){
            for (User user : userList) {
                if (user.getChatId() != null && user.getChatId().equals(update.getMessage().getChatId().toString())){
                    return user;
                }
            }
        } else if (update.hasCallbackQuery()){
            for (User user : userList) {
                if (user.getChatId() != null && user.getChatId().equals(update.getCallbackQuery().getMessage().getChatId().toString())){
                    return user;
                }
            }
        }


        User newUser = new User();
        newUser.setFullName(update.getMessage().getFrom().getFirstName());
        newUser.setUsername(update.getMessage().getFrom().getUserName());
        newUser.setChatId(update.getMessage().getChatId().toString());
        newUser.setBalance(100000);
        // newUser.setStage();

        UserActivity userActivity = new UserActivity();
        userActivity.setUser(newUser);
        Db.userActivityMap.put(newUser.getChatId(), userActivity);


        userList.add(newUser);
        return newUser;
    }

}
