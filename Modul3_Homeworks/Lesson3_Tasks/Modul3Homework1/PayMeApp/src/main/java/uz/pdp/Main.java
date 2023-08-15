package uz.pdp;

import org.omg.CORBA.INITIALIZE;
import uz.pdp.model.User;
import uz.pdp.servis.UserServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws InputMismatchException {
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        UserServiceImpl userService = new UserServiceImpl();

        while (true) {
            System.out.println("1.Log in\n2.Register\n3.Back");
            int option = scannerInt.nextInt();
            switch (option) {
                case 1:
                    User user = userService.login();
                    if (user != null){
                        userService.userMenu(user);
                    }else {
                        System.out.println("User not found!!!");
                    }
                    break;
                case 2:
                    userService.register();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Wrong option!!!");
            }
        }


    }
}
