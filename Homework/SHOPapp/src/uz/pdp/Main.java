package uz.pdp;

import uz.pdp.model.Cloth;
import uz.pdp.model.User;
import uz.pdp.model.enums.Size;
import uz.pdp.model.enums.Status;
import uz.pdp.service.CustomerServiceImpl;
import uz.pdp.service.SellerServiceImpl;
import uz.pdp.service.UserServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        UserServiceImpl userService = new UserServiceImpl();
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        SellerServiceImpl sellerService = new SellerServiceImpl();


        /*
        while (true) {
            try {
                option = scannerInt.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong type of input!!!");
                scannerStr.nextLine();
            }
        }
         */

        while (true){
            System.out.println("1.Buy a cloth\n2.My cart\n3.Login\n4.Register\n5.Exit");
            int option = scannerInt.nextInt();
                switch (option){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            User user = userService.login();
                            if(user != null){
                                switch (user.getRole()){
                                    case CUSTOMER:
                                        customerService.customerMenu(user);
                                        break;
                                    case SELLER:
                                        sellerService.sellerMenu(user);
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
