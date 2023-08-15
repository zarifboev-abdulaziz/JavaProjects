package uz.pdp;

import uz.pdp.model.Cloth;
import uz.pdp.model.PayType;
import uz.pdp.model.Transaction;
import uz.pdp.model.User;
import uz.pdp.model.enums.Role;
import uz.pdp.model.enums.Size;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {

    public static User admin = new User(10, "Aziz", "a", "1", true, 0, Role.ADMIN);

    public static List<User> userList = new ArrayList<>(Arrays.asList(
           admin,
            new User(11, "Asilbek", "c", "1", true, 50000, Role.CUSTOMER, new ArrayList<>())
    ));




    public static List<Cloth> clothList = new ArrayList<>(Arrays.asList(
            new Cloth(100, "Shapka", "White", Size.S, 16000, 0, 5),
            new Cloth(101, "T-Shirt", "Blue", Size.XL, 25000, 0, 5),
            new Cloth(102, "Dress", "Red", Size.M, 55000, 0, 5),
            new Cloth(103, "Sweater", "Green", Size.M, 55000, 0, 5),
            new Cloth(104, "Shim", "Black", Size.XL, 75000, 0, 5)
    ));

    public static List<Cloth> temporaryCart = new ArrayList<>();

    public static List<PayType> payTypeList = new ArrayList<>(Arrays.asList(
            new PayType(1000, "Click", 1),
            new PayType(1001, "Payme", 2),
            new PayType(1002, "ZoodPay", 0),
            new PayType(1003, "Oson", 0.5)
    ));

    public static List<Transaction> transactionList = new ArrayList<>();


    public static void main(String[] args) {
        File file = new File("PurchasedHistory.txt");

        try {
          file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
