package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.Cloth;
import uz.pdp.model.User;
import uz.pdp.model.enums.Status;
import uz.pdp.service.interfaces.TemporaryMyCartService;

import java.util.Scanner;

public class TemporaryMyCartServiceImpl implements TemporaryMyCartService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    UserServiceImpl userService = new UserServiceImpl();
    CustomerServiceImpl customerService = new CustomerServiceImpl();
    SellerServiceImpl sellerService = new SellerServiceImpl();



    @Override
    public void buyClothWithoutRegister() {
        for (Cloth cloth : Store.clothList) {
            if (cloth.getStatus() == Status.ONSALE){
                System.out.println(cloth);
            }
        }

        System.out.println("Enter Cloth id to add your cart: ");
        int inputId = scannerInt.nextInt();
        for (Cloth cloth : Store.clothList) {
            if(cloth.getId() == inputId){
                Store.temporaryMyCart.add(cloth);
            }
        }
    }

    @Override
    public void myCartMenu() {
        while (true){
            System.out.println("1.Show cloth List\n2.Checkout\n3.Check Average price\n4.Back");
            int cartOption = scannerInt.nextInt();
            switch (cartOption){
                case 1:
                    clothList();
                    break;
                case 2:
                    //Chekout
                    break;
                case 3:
                    checkAveragePrice();
                    break;

                case 4: return;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }

    }

    @Override
    public void clothList() {
        for (Cloth cloth : Store.temporaryMyCart) {
            System.out.println(cloth);
        }
    }

    @Override
    public void checkOut() {
        System.out.println("Please log in or register in order to check out!!! ");
        System.out.println("1.Login\n2.Register\n3.Back");
        int checkOption = scannerInt.nextInt();
        switch (checkOption){
            case 1:
                User user = userService.login();
                if(user != null){
                    switch (user.getRole()){
                        case CUSTOMER:
                            customerService.customerMenu(user);
                            break;
                        case SELLER:
                            sellerService.sellerMenu(user);
                            break;
                    }
                    //TODO
                }
            case 2:
               userService.register();
                break;

            case 3: return;
            default:
                System.out.println("Wrong option!!!");
                break;
        }
    }

    @Override
    public void checkAveragePrice() {
        int count = 0;
        int sumPrice = 0;

        for (Cloth cloth : Store.myCart) {
            count++;
            sumPrice += cloth.getPrice();
        }

        try {
            System.out.println("Average price: " + (sumPrice/count));
        }catch (ArithmeticException e){
            System.out.println("Your cart is empty!!!");
        }

    }
}
