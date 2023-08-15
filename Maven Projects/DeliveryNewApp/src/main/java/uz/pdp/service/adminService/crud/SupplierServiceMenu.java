package uz.pdp.service.adminService.crud;

import uz.pdp.DataBase;
import uz.pdp.enums.Role;
import uz.pdp.model.User;

import java.util.Scanner;

public class SupplierServiceMenu {

    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    public void showMenu() {
        System.out.println("=== Supplier Menu ===");
        System.out.println("1.Add supplier\n2.Read supplier\n3.Update supplier\n4.Delete supplier\n5.Back");
        int optionSupplier = scannerInt.nextInt();
        switch (optionSupplier) {
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

        int id =(int) (Math.random() * (9999 - 1000)) + 1000;

        System.out.println("Enter the supplier fullName:");
        String fullname = scannerStr.nextLine();

        System.out.println("Enter the supplier email: ");
        String email = scannerStr.nextLine();

        System.out.println("Enter the supplier password:  ");
        String password = scannerStr.nextLine();

        User user = new User(id,fullname,email,password,0, Role.SUPPLIER);
        DataBase.userList.add(user);
        System.out.println("Successfully created:");

    }


    public void read() {
        System.out.println("=== Suppliers List ===");
        for (User user : DataBase.userList) {
            if(user.getRole() == Role.SUPPLIER){
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
        System.out.println("=== Suppliers List ===");
        read();

        System.out.println("Enter the supplier's id to update: ");
        int supplierId = scannerInt.nextInt();

        for (User user : DataBase.userList) {
            if(user.getRole() == Role.SUPPLIER && user.getId() == supplierId){
                System.out.println("Enter the supplier new fullname: ");
                String newSupplierFullName = scannerStr.nextLine();

                System.out.println("Enter the supplier new email: ");
                String newSupplierEmail = scannerStr.nextLine();

                System.out.println("Enter the supplier new password: ");
                String newSupplierPassword = scannerStr.nextLine();

                user.setFullName(newSupplierFullName);
                user.setEmail(newSupplierEmail);
                user.setPassword(newSupplierPassword);

                System.out.println("Successfully updated!!!");
            }
        }
    }


    public void delete() {
        System.out.println("=== Suppliers List ===");
        read();

        System.out.println("Enter the supplier's id to delete: ");
        int supplierId = scannerInt.nextInt();

        DataBase.userList.removeIf(user -> user.getRole() == Role.SUPPLIER && user.getId() == supplierId);
        System.out.println("Successfully deleted!");
    }
}
