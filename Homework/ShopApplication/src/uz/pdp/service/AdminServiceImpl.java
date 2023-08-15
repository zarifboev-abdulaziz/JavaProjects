package uz.pdp.service;

import uz.pdp.DataBase;
import uz.pdp.model.Transaction;
import uz.pdp.model.User;
import uz.pdp.service.interfaces.AdminService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminServiceImpl implements AdminService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    ClothServiceImpl clothService = new ClothServiceImpl();
    ClientServiceForAdminImpl clientServiceForAdmin = new ClientServiceForAdminImpl();
    PayTypeServiceImpl payTypeService = new PayTypeServiceImpl();

    @Override
    public void adminMenu(User user) throws InputMismatchException {
        while (true){
            System.out.println("1.Cloth menu\n2.My Client menu\n3.Pay type menu\n4.Transaction History\n5.Log out");
            while (true){
                int option = scannerInt.nextInt();
                switch (option){
                    case 1:
                        clothService.clothMenu(user);
                        break;
                    case 2:
                        clientServiceForAdmin.clientMenu(user);
                        break;
                    case 3:
                        payTypeService.payTypeMenu(user);
                        break;
                    case 4:
                        transactionHistory();
                        break;

                    case 5: return;
                    default:
                        System.out.println("Wrong option");
                        break;
                }
            }
        }
    }

    @Override
    public void transactionHistory() {
        for (Transaction transaction : DataBase.transactionList) {
            System.out.println(transaction);
        }
    }
}
