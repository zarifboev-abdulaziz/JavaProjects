package uz.pdp.service.adminService.crud;

import uz.pdp.DataBase;
import uz.pdp.enums.Role;
import uz.pdp.model.User;


import java.util.Scanner;

public class ChiefServiceMenu  {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    public void showMenu() {
        System.out.println("=== Chief Menu ===");
        System.out.println("1.Add chief\n2.Read chief\n3.Update chief\n4.Delete chief\n5.Back");
        int optionChief = scannerInt.nextInt();
        switch (optionChief) {
            case 1:
                create();
                break;
            case 2:
                read();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;
            case 5:
                return;
            default:
                System.out.println("Wrong option!");
                break;
        }
        showMenu();
    }


    public void create() {

        int id = (int) (Math.random() * (9999 - 1000)) + 1000;

        System.out.println("Enter the chief fullName: ");
        String fullName = scannerStr.nextLine();

        System.out.println("Enter an email of the chief: ");
        String email = scannerStr.nextLine();

        System.out.println("Enter the chief password: ");
        String password = scannerStr.nextLine();

        User user = new User(id,fullName,email,password,0, Role.CHIEF);
        DataBase.userList.add(user);
        System.out.println("Successfully created!");
    }

//    private int id;
//    private String fullName;
//    private String email;
//    private String password;
//    private double balance;
//    private List<Cart> myCart;
//    private Role role;


    public void read() {
        System.out.println("=== Chiefs List ===");
        for (User user : DataBase.userList) {
            if(user.getRole() == Role.CHIEF){
                System.out.println(
                        "Id: "       +  user.getId() + " | " +
                        "FullName: " +  user.getFullName() + " | " +
                        "Email: "    +  user.getEmail() + " | " +
                        "Password: " +  user.getPassword() + " | " +
                        "Balance: "  +  user.getBalance() + " | " +
                        "MyCart: "   +  user.getMyCart() + " | " +
                        "Role: "     +  user.getRole());
                System.out.println("============================================================================================");
            }
        }
    }


    public void update() {

        read();
        System.out.println("Enter the chief's id to update: ");
        int chiefId = scannerInt.nextInt();

        for (User user : DataBase.userList) {
            if(user.getId() == chiefId){

                System.out.println("Enter chief's new fullName: ");
                String newFullName = scannerStr.nextLine();

                System.out.println("Enter chief's new email: ");
                String newEmail = scannerStr.nextLine();

                System.out.println("Enter chief's new password: ");
                String newPassword = scannerStr.nextLine();

                user.setFullName(newFullName);
                user.setEmail(newEmail);
                user.setPassword(newPassword);

                System.out.println("Successfully updated!");
            }
        }

    }


    public void delete() {
        System.out.println("=== Chiefs List ===");
        read();

        System.out.println("Enter the chiefs id to delete: ");
        int chiefId = scannerInt.nextInt();

        DataBase.userList.removeIf(user -> user.getId() == chiefId);
        System.out.println("Successfully deleted!");
    }
}
