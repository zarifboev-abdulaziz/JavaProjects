package uz.pdp.utils;

import uz.pdp.model.*;
import uz.pdp.model.abs.User;
import uz.pdp.model.enums.Size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Db {

    public static User admin = new Admin("admin", "a", "1", 0);

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
}
