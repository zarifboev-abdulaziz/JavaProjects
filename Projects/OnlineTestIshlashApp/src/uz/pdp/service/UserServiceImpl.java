package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.User;
import uz.pdp.model.enums.Role;
import uz.pdp.service.interfaceServis.UserService;

import java.util.Scanner;

public class UserServiceImpl implements UserService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public User login() {

        System.out.println("Enter your userName: ");
        String inputUserName = scannerStr.nextLine();

        System.out.println("Enter your Password: ");
        String inputPassword = scannerStr.nextLine();

        boolean isFound = false;

        for (User user : Store.userList) {
            if(user.getUserName().equals(inputUserName) && user.getPassword().equals(inputPassword)){
                isFound = true;
                return user;
            }
        }
        if(!isFound){
            System.out.println("User not found");
        }
        return null;

    }

    @Override
    public void register() {

        System.out.println("Enter your full Name: ");
        String fullName = scannerStr.nextLine();

        System.out.println("Enter your userName: ");
        String userName = scannerStr.nextLine();

        System.out.println("Enter your password: ");
        String password = scannerStr.nextLine();

        User user = new User((int)(Math.random()*10000), fullName, userName,  password, Role.ABITURIENT, 0);
        Store.userList.add(user);

        System.out.println("User successfully created");

    }

    @Override
    public void checkBalance(User user) {
        System.out.println(user.getBalance());
    }
}
