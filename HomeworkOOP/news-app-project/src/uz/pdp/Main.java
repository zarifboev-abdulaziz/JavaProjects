package uz.pdp;

import uz.pdp.model.News;
import uz.pdp.model.User;
import uz.pdp.model.enums.Role;
import uz.pdp.model.enums.Status;
import uz.pdp.model.payment.PaymentMethod;
import uz.pdp.model.payment.Transaction;
import uz.pdp.service.UserServiceImpl;

import java.util.*;

public class Main {

    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    static UserServiceImpl userService = new UserServiceImpl();

    public static List<Transaction> transactionList = new ArrayList<>();

    static List<PaymentMethod> paymentMethods = new ArrayList<>(
            Arrays.asList(
                    new PaymentMethod(1, "PAYME", 1),
                    new PaymentMethod(2, "CLICK", 0.5),
                    new PaymentMethod(3, "APELSIN", 0.3)
            )
    );


    static Map<String, User> userMap = new HashMap<String, User>() {{
        put("admin@mail.com", new User(
                1,
                "Admin Adminov",
                "admin@mail.com",
                "12345",
                Role.ADMIN,
                20000

        ));
        put("user1@mail.com", new User(
                2,
                "Asadbek Halimjonov",
                "user1@mail.com",
                "12345",
                Role.JOURNALIST,
                0
        ));
    }};

    public static List<News> newsList = new ArrayList<News>(Arrays.asList(
            new News(1, "Title test", "Body test", userMap.get("user1@mail.com"),
                    Status.NEW),
            new News(2, "Title test 2", "Body test 2", userMap.get("user1@mail.com"),
                    Status.NEW),
            new News(3, "Title test 3", "Body test 3", userMap.get("user1@mail.com"),
                    Status.NEW),
            new News(4, "Title test 4", "Body test 4", userMap.get("user1@mail.com"),
                    Status.NEW)
    ));

    public static void main(String[] args) {
        while (true) {
            System.out.println("1=> Login, 2=> Register 0=> Exit");
            int option = scannerInt.nextInt();

            switch (option) {
                case 1:
                    userService.login(userMap, newsList, transactionList, paymentMethods);
                    break;
                case 2:
                    userService.register(userMap);
                    break;
                case 0:
                    return;
            }
        }

    }
}
