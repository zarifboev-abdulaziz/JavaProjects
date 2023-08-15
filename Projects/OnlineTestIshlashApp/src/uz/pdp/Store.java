package uz.pdp;

import uz.pdp.model.*;
import uz.pdp.model.enums.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Store {

    public static List<User> userList = new ArrayList<>(Arrays.asList(
            new User(10, "Admin", "admin", "123", Role.ADMIN, 10000),
            new User(11, "user", "user", "123", Role.ABITURIENT, 0)
    ));

    public static List<Subject> subjectList = new ArrayList<>(Arrays.asList(
            new Subject(111, "Matematika", 0, 0, 5, 1233)
    ));

    public static List<Test> testList = new ArrayList<>();

    public static List<FillBalanceHistory> fillBalanceHistoryList = new ArrayList<>();

    public static List<UserTestSolveHistory> userTestSolveHistoryList = new ArrayList<>();

    public static List<Answer> answerList = new ArrayList<>();

    public static List<PayType> payTypeList = new ArrayList<>(Arrays.asList(
            new PayType(12, "Click", 1),
            new PayType(13, "Payme", 2)
    ));

    public static User sessionUser;




}
