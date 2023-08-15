package uz.pdp;

import uz.pdp.enums.Role;
import uz.pdp.enums.Type;
import uz.pdp.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataBase {

    public static User admin = new User(100, "Admin", "a", "1", 10000, Role.ADMIN);

    public static List<User> userList = new ArrayList<>(Arrays.asList(
           admin,
            new User(101, "client", "c", "2", 10000, new ArrayList<>(), Role.CLIENT),
            new User(102, "sup", "s", "3", 10000, Role.SUPPLIER),
            new User(103, "ch", "ch", "4", 10000, Role.CHIEF)
    ));
    public static List<Food> foodList = new ArrayList<>(Arrays.asList(
            new Food(1, "Hamburger", 5000, Type.FAST_FOOD),
            new Food(2, "Coca_Cola", 7000, Type.DRINK),
            new Food(3, "Cake", 10000, Type.DESERT)
    ));
    public static List<OrderHistory> orderHistoryList = new ArrayList<>();

    public static List<Order> orderList = new ArrayList<>();

    public static List<PayType> payTypeList = new ArrayList<>(Arrays.asList(
            new PayType(10, "CLICK", 1.0),
            new PayType(11, "PAY ME", 0.5)
    ));
    public static List<Cart> temporaryCart = new ArrayList<>();

}
