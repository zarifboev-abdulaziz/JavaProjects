package uz.pdp.service;

import com.google.gson.Gson;
import uz.pdp.DataBase;
import uz.pdp.model.Cloth;
import uz.pdp.model.User;
import uz.pdp.model.enums.Role;
import uz.pdp.service.interfaces.UserService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
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

        for (User user : DataBase.userList) {
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

        List<Cloth> myCart = new ArrayList<>();

        User user = new User(id, name, email, password, true, 0, Role.CUSTOMER, myCart);
        DataBase.userList.add(user);
        System.out.println("Success!!!");

        try (Writer writer = new FileWriter("src/main/resources/UserList.json")) {
            Gson gson = new Gson();

            String s = gson.toJson(DataBase.userList);
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
