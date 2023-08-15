package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.Cloth;
import uz.pdp.model.User;
import uz.pdp.service.interfaces.UserMyCartService;

import java.util.Scanner;

public class UserMyCartServiceImpl implements UserMyCartService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public void myCartMenu(User user) {
        while (true){
            System.out.println("1.ClothList\n2.Check Out\n3.Check average Price\n4.Back");
            int option = scannerInt.nextInt();
            switch (option){
                case 1: break;
                case 2: break;
                case 3: break;

                case 4: return;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }

    }

    @Override
    public void clothList(User user) {
        for (Cloth cloth : Store.myCart) {
            System.out.println(cloth);
        }
    }

    @Override
    public void checkOut(User user) {
        for (Cloth cloth : Store.myCart) {
            System.out.println(cloth);
        }

        System.out.println("Enter cloth id to buy!!!");
        int inputId = scannerInt.nextInt();

        boolean isFound = false;
        for (Cloth cloth : Store.clothList) {
            if (cloth.getId() == inputId){
                isFound = true;

                //Transaction process
                if (user.getBalance() < cloth.getPrice()){
                    System.out.println("Hisobda yetarli mablag' yuq!!!");
                    return;
                }
                user.setBalance(user.getBalance() - cloth.getPrice());
                for (User user1 : Store.userList) {

                }
                cloth.setOwnerId(user.getId());
            }
        }
    }

    @Override
    public void checkAveragePrice(User user) {

    }
}
