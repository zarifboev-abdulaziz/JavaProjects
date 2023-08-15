package uz.pdp;

import uz.pdp.model.User;
import uz.pdp.service.AdminServiceImpl;
import uz.pdp.service.UserServiceImpl;
import uz.pdp.serviceCustomerService.ClientServiceImpl;
import uz.pdp.serviceCustomerService.GuestCartServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        UserServiceImpl userService = new UserServiceImpl();
        ClientServiceImpl clientService = new ClientServiceImpl();
        AdminServiceImpl adminService = new AdminServiceImpl();
        GuestCartServiceImpl guestCartService = new GuestCartServiceImpl();

        while (true){
            System.out.println("1.Buy a cloth\n2.My cart\n3.Login\n4.Register\n5.Exit");
            int option = scannerInt.nextInt();
            switch (option){
                case 1:
                    guestCartService.guestBuyCloth();
                    break;
                case 2:
                    guestCartService.guestCartMenu();
                    break;
                case 3:
                    User user = userService.login();
                    if(user != null){
                        switch (user.getRole()){
                            case CUSTOMER:
                                clientService.customerMenu(user);
                                break;
                            case ADMIN:
                                try {
                                    adminService.adminMenu(user);
                                }catch (InputMismatchException e){
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
