package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.User;
import uz.pdp.model.enums.Role;
import uz.pdp.service.interfaces.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public User login() {

        System.out.println("Enter your email: ");
        String email = scannerStr.nextLine();

        System.out.println("Enter your password: ");
        String password = scannerStr.nextLine();

        for (User user : Store.userList) {
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }

        return null;
    }

    @Override
    public void register() {
        int id = (int)(Math.random()*10000);

        System.out.println("Enter your name: ");
        String name = scannerStr.nextLine();

        System.out.println("Enter your email: ");
        String email = scannerStr.nextLine();

        System.out.println("Enter your password: ");
        String password = scannerStr.nextLine();

        System.out.print("Choose your role:\n1.Customer\n2.Seller\n=>");
        int option = scannerInt.nextInt();
        Role role = null;
        try{
            switch (option){
                case 1:
                    role  =Role.CUSTOMER;
                    break;
                case 2:
                    role = Role.SELLER;
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }catch (InputMismatchException e){
            System.out.println(e.fillInStackTrace());
        }


        User user = new User(id, name, email, password, 0, role);
        Store.userList.add(user);
        System.out.println("Successfully created");
    }


}
