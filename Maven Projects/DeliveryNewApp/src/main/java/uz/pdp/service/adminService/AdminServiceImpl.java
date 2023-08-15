package uz.pdp.service.adminService;

import uz.pdp.DataBase;
import uz.pdp.enums.Role;
import uz.pdp.model.User;
import uz.pdp.service.adminService.crud.ChiefServiceMenu;
import uz.pdp.service.adminService.crud.PayTypeServiceImpl;
import uz.pdp.service.adminService.crud.ProductServiceImpl;
import uz.pdp.service.adminService.crud.SupplierServiceMenu;
import uz.pdp.service.supplierService.SupplierServiceImpl;

import java.util.Scanner;

public class AdminServiceImpl implements AdminService {

    public static ProductServiceImpl productService = new ProductServiceImpl();
    PayTypeServiceImpl payTypeService = new PayTypeServiceImpl();


    @Override
    public void adminMenu(User user) {


        System.out.println("[1] Clients menu | [2] Suppliers menu | [3] Chefs menu | [4] Products menu | [5] PayType menu | [6] Order History | [7] Show balance | [8] Back ");
        System.out.print("-> ");
        int option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1:
                clientsMenu();
                break;
            case 2:
                supplierMenu();
                break;
            case 3:
                chiefsMenu();
                break;
            case 4:
                foodsMenu();
                break;
            case 5:
                payTypeMenu();
                break;
            case 6:
                orderHistory();
                break;
            case 7:
                showBalance();
                break;
            case 8:
                return;
            default:
                System.out.println("Wrong option!!! please try again!!!");
                break;
        }
        adminMenu(user);
    }

    @Override
    public void clientsMenu() {
        for (User user : DataBase.userList) {
            if (user.getRole() == Role.CLIENT){
                System.out.println(user);
            }
        }
    }

    @Override
    public void supplierMenu() {
        SupplierServiceMenu supplierServiceMenu = new SupplierServiceMenu();
        supplierServiceMenu.showMenu();
    }

    @Override
    public void chiefsMenu() {
        ChiefServiceMenu chiefServiceMenu = new ChiefServiceMenu();
        chiefServiceMenu.showMenu();

    }

    @Override
    public void foodsMenu() {
        productService.productMenu();
    }

    @Override
    public void payTypeMenu() {
        payTypeService.payTypeMenu();
    }

    @Override
    public void orderHistory() {
        DataBase.orderHistoryList.forEach(System.out::println);
    }

    @Override
    public void showBalance() {
        for (User user : DataBase.userList) {
            if (user.getRole().equals(Role.ADMIN)) {
                System.out.println("You are balance => " + user.getBalance());
            }
        }
    }
}
