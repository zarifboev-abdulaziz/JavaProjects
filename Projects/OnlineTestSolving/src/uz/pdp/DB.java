package uz.pdp;

import uz.pdp.model.*;
import uz.pdp.model.enums.Role;

import java.util.*;

public class DB {

    public static Scanner scannerInt = new Scanner(System.in);
    public static Scanner scannerStr = new Scanner(System.in);

    public static List<User> userList = new ArrayList<>(Arrays.asList(
            new User(10, "Abdulaziz", "admin", "12345", Role.ADMIN, 10000),
            new User(11, "Javohir", "user", "1234", Role.ABITURIENT, 0)
    ));

    public static List<Subject> subjectList = new ArrayList<>();

    public  static List<PayType> payTypeList = new ArrayList<>();

    public static List<Test> testList = new ArrayList<>();

    public static List<Answer> answerList = new ArrayList<>();

    public static List<FillBalance> fillBalanceList = new ArrayList<>();

}
