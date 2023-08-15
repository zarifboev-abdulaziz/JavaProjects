package uz.pdp.service;

import uz.pdp.DB;
import uz.pdp.model.User;
import uz.pdp.model.enums.Role;
import uz.pdp.service.interfaces.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public void register() {
        int id = (int)(Math.random()*10000);

        System.out.println("Enter your FullName: ");
        String fullName = DB.scannerStr.nextLine();

        System.out.println("Enter your email: ");
        String email = DB.scannerStr.nextLine();

        System.out.println("Enter your password: ");
        String password = DB.scannerStr.nextLine();

        //Checking user existence
        boolean isFound = true;
        for (User user : DB.userList) {
            if(user.getEmail().equals(email)){
                isFound = false;
                break;
            }
        }

        if(isFound){
            User user = new User(id, fullName, email, password, Role.ABITURIENT, 0);
            DB.userList.add(user);
            System.out.println("User successfully created\n");
        } else {
            System.out.println("User exists.\n");
        }

    }

    @Override
    public User login() {
        System.out.println("Enter your email: ");
        String email = DB.scannerStr.nextLine();

        System.out.println("Enter your password: ");
        String password = DB.scannerStr.nextLine();

        for (User user : DB.userList) {
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
