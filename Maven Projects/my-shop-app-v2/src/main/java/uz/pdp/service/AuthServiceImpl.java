package uz.pdp.service;

import uz.pdp.model.Customer;
import uz.pdp.model.OrderItem;
import uz.pdp.model.abs.User;
import uz.pdp.service.interfaces.AuthService;
import uz.pdp.utils.DataFromJson;

import java.util.ArrayList;

import static uz.pdp.utils.Util.*;
import static uz.pdp.utils.Db.*;

public class AuthServiceImpl implements AuthService {
    @Override
    public User login() {
        print(BLUE, "username (0=> Back): ");
        String inputUsername = scnStr();
        if (inputUsername.equals("0")) return null;

        print(BLUE, "password: ");
        String inputPassword = scnStr();

        boolean isFound = false;
        User selectedUser = null;
        for (User user : userList) {
            if (user.getUsername().equals(inputUsername)) {
                isFound = true;
                selectedUser = user;
                break;
            }
        }

        if (!isFound || !selectedUser.getPassword().equals(inputPassword)) {
            System.out.println(printInRed("Invalid username or password"));
            return login();
        } else {
            return selectedUser;
        }

    }

    @Override
    public void register() {
        System.out.println("Enter fullName: ");
        String fullName = scnStr();

        System.out.println("Enter UserName: ");
        String userName = scnStr();

        for (User user : userList) {
            if (user.getUsername().equals(userName)){
                System.err.println("This UserName exists!!!");
                return;
            }
        }

        System.out.println("Enter password: ");
        String password = scnStr();

        Customer customer = new Customer(fullName, userName, password, 0, new ArrayList<OrderItem>());
        userList.add(customer);

        DataFromJson.updateJson();
    }

    @Override
    public void logout(User authorizedUser) {
        authorizedUser = null;
    }
}
