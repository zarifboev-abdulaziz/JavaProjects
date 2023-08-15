package uz.pdp;

import uz.pdp.model.Cloth;
import uz.pdp.model.User;
import uz.pdp.model.enums.Role;
import uz.pdp.model.enums.Size;
import uz.pdp.model.enums.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Store {

    public static List<User> userList = new ArrayList<>(Arrays.asList(
            new User(10, "Aziz", "aziz", "12345", 0, Role.CUSTOMER),
            new User(11, "Nodirbek", "seller", "12345", 0, Role.SELLER),
            new User(11, "Abdulaziz", "admin", "12345", 10000, Role.ADMIN)
    ));

    public static List<Cloth> clothList = new ArrayList<>(Arrays.asList(
            new Cloth(100, "T-shirt", 11, "White", Size.M, 20000, Status.ONSALE),
            new Cloth(101, "Jacket", 11, "Blue", Size.XL, 70000, Status.ONSALE)
    ));

    public static List<Cloth> myCart = new ArrayList<>();

    public static List<Cloth> temporaryMyCart = new ArrayList<>();

}
