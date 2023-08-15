package uz.pdp;

import uz.pdp.model.User;
import uz.pdp.service.AbiturientServiceImpl;
import uz.pdp.service.AdminServiceImpl;
import uz.pdp.service.UserServiceImpl;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        UserServiceImpl userService = new UserServiceImpl();
        AdminServiceImpl adminService = new AdminServiceImpl();
        AbiturientServiceImpl abiturientService = new AbiturientServiceImpl();



        while (true) {
            System.out.println("Welcome to Online Test Platform!!!");
            System.out.println("1.Login\n2.Register\n3.Exit\nTanlang: ");
            int option = scannerInt.nextInt();
            switch (option) {
                case 1:
                    User user = userService.login();
                    if (user != null) {
                        switch (user.getRole()) {
                            case ADMIN:
                                adminService.showAdminMenu(user);
                                break;

                            case ABITURIENT:
                                abiturientService.showAbiturientMenu(user);
                                break;
                        }

                    }
                    break;
                case 2:
                    userService.register();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Wrong option");
                    break;
            }


        }
     }
}
