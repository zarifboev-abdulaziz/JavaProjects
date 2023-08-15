package uz.pdp.service;

import uz.pdp.model.News;
import uz.pdp.model.User;
import uz.pdp.model.enums.Role;
import uz.pdp.model.enums.Status;
import uz.pdp.model.payment.PaymentMethod;
import uz.pdp.model.payment.Transaction;
import uz.pdp.service.interfaceSer.UserService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    static AdminServiceImpl adminService = new AdminServiceImpl();
    static JournalistServiceImpl journalistService = new JournalistServiceImpl();

    @Override
    public void login(Map<String, User> userMap, List<News> newsList, List<Transaction> transactionList, List<PaymentMethod> paymentMethods) {
        System.out.println("Input email: ");
        String inputEmail = scannerStr.nextLine();
        System.out.println("Input password: ");
        String inputPassword = scannerStr.nextLine();


        if (!userMap.containsKey(inputEmail) ||
                (userMap.containsKey(inputEmail) && !userMap.get(inputEmail).getPassword().equals(inputPassword))) {
            System.out.println("Invalid email or password");
            return;
        }
        User authorizedUser = userMap.get(inputEmail);
        switch (authorizedUser.getRole()) {
            case ADMIN:
                System.out.println("====Welcome " + authorizedUser.getFullName() + "====");
                adminService.showAdminMenu(userMap,
                        newsList,
                        authorizedUser,
                        transactionList,
                        paymentMethods);
                break;
            case JOURNALIST:
                System.out.println("====Welcome " + authorizedUser.getFullName() + "====");
                journalistService.showJournalistMenu(newsList, authorizedUser, userMap);
                break;
        }

    }

    @Override
    public void register(Map<String, User> userMap) {
        System.out.println("Input full name: ");
        String inputFullName = scannerStr.nextLine();
        System.out.println("Input email: ");
        String inputEmail = scannerStr.nextLine();
        while (userMap.containsKey(inputEmail)) {
            System.out.println("This email is already taken!!!");
            System.out.println("Input email (0=> Back): ");
            inputEmail = scannerStr.nextLine();
            if (inputEmail.equals("0")) return;
        }
        System.out.println("Input password: ");
        String inputPassword = scannerStr.nextLine();

        userMap.put(inputEmail, new User(
                (int) (Math.random() * 10000),
                inputFullName,
                inputEmail,
                inputPassword
        ));
        System.out.println("Successfully registered!!!");
        return;
    }

    @Override
    public void checkBalance(User user) {
        System.out.println("Balance: " + user.getBalance());
    }

    @Override
    public void showAllNews(List<News> newsList, User user) {

        if (user.getRole().equals(Role.ADMIN)) {

            for (News news : newsList) {

                System.out.println("id: " + news.getId() + "\n" +
                        "author: " + news.getAuthor().getFullName() + "\n" +
                        "title: " + news.getTitle() + "\n" +
                        "stats : " + news.getStatus() + "\n" +
                        "=====================================");

            }

        } else {

            newsList.stream().filter(news -> news.getStatus().equals(Status.ACCEPTED)).forEach(
                    news -> {
                        System.out.println("Author : " + news.getAuthor().getFullName());
                        System.out.println("Description : " + news.getBody());
                        System.out.println("Status : " + news.getStatus());
                    }
            );

        }

    }
}
