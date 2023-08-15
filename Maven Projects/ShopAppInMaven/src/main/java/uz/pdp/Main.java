package uz.pdp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uz.pdp.model.Transaction;
import uz.pdp.model.User;
import uz.pdp.service.AdminServiceImpl;
import uz.pdp.service.UserServiceImpl;
import uz.pdp.serviceCustomerService.ClientServiceImpl;
import uz.pdp.serviceCustomerService.GuestCartServiceImpl;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Loading users from Database

        try (Reader reader = new FileReader("src/main/resources/UserList.json")) {
            Type type = new TypeToken<ArrayList<User>>() {}.getType();
            List<User> userListFromJson = new Gson().fromJson(reader, type);
            if (userListFromJson != null){
                DataBase.userList.addAll(userListFromJson);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Loading purchased history List
        try (Reader reader = new FileReader("src/main/resources/History.json")) {
            Type type = new TypeToken<ArrayList<Transaction>>() {}.getType();
            List<Transaction> transactionListFromJson = new Gson().fromJson(reader, type);
            if (transactionListFromJson != null) DataBase.transactionList.addAll(transactionListFromJson);

        } catch (IOException e) {
            e.printStackTrace();
        }


        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        UserServiceImpl userService = new UserServiceImpl();
        ClientServiceImpl clientService = new ClientServiceImpl();
        AdminServiceImpl adminService = new AdminServiceImpl();
        GuestCartServiceImpl guestCartService = new GuestCartServiceImpl();

        while (true) {
            System.out.println("1.Buy a cloth\n2.My cart\n3.Login\n4.Register\n5.Exit");
            int option = scannerInt.nextInt();
            switch (option) {
                case 1:
                    guestCartService.guestBuyCloth();
                    break;
                case 2:
                    guestCartService.guestCartMenu();
                    break;
                case 3:
                    User user = userService.login();
                    if (user != null) {
                        switch (user.getRole()) {
                            case CUSTOMER:
                                clientService.customerMenu(user);
                                break;
                            case ADMIN:
                                try {
                                    adminService.adminMenu(user);
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong type of input!!!");
                                }
                                break;
                        }
                    }
                    break;
                case 4:
                    userService.register();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("wrong option");
                    break;
            }

        }


    }
}
