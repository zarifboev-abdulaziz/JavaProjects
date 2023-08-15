package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.User;
import uz.pdp.model.enums.Role;


import java.util.Scanner;

public class UserServiceImpl implements UserService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    @Override
    public User login() {
        System.out.println("Enter your email: ");
        String email = scannerStr.nextLine();

        System.out.println("Enter password: ");
        String password = scannerStr.nextLine();



        return null;
    }

    @Override
    public void register() {
        int id = (int)(Math.random()*10000);

        System.out.println("Enter name: ");
        String name = scannerStr.nextLine();

        System.out.println("Enter email: ");
        String email = scannerStr.nextLine();

        System.out.println("ENter your password: ");
        String password = scannerStr.nextLine();

        Role role = null;
        System.out.println("Choose your role: \n1.Customer\n2.Seller\n");
        int option = scannerInt.nextInt();
        switch (option){
            case 1:
                role = Role.Customer;
            break;
            case 2:
                role = Role.Seller;
                break;
            default:
                System.out.println("wrong option!!!");
                break;
        }

        User user = new User(id, name, email, password,0, role);
        Store.userList.add(user);
        System.out.println("Success!!!");
    }
}
