package uz.pdp.service.authService;

import uz.pdp.DataBase;
import uz.pdp.enums.Role;
import uz.pdp.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class AuthServiceImpl implements AuthService {
    @Override
    public void register() {
        System.out.print("Enter full name: ");
        String fullName = new Scanner(System.in).nextLine();
        System.out.print("Enter email: ");
        String email = new Scanner(System.in).nextLine();
        System.out.print("Enter password: ");
        String password = new Scanner(System.in).nextLine();

        for (User user : DataBase.userList) {
            if (user.getEmail().equals(email)) {
                System.out.println("Already exists!!! ");
                return;
            }
        }

        int id = (int)(Math.random()*10000);
        User user = new User(id,fullName,email,password,0,new ArrayList<>(), Role.CLIENT);
        DataBase.userList.add(user);

    }

    @Override
    public User logIn() {
        System.out.print("Enter your email: ");
        String email = new Scanner(System.in).nextLine();
        System.out.print("Enter your password: ");
        String password = new Scanner(System.in).nextLine();

        for (User user : DataBase.userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }
}
