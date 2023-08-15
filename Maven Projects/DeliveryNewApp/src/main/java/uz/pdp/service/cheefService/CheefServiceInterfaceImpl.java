package uz.pdp.service.cheefService;

import uz.pdp.DataBase;
import uz.pdp.enums.Status;
import uz.pdp.enums.Type;
import uz.pdp.model.Cart;
import uz.pdp.model.Order;
import uz.pdp.model.User;

import java.util.Scanner;

public class CheefServiceInterfaceImpl implements CheefServiceInterface {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    public static boolean isFound = false;

    @Override
    public void showCheefMenu(User cheef) {
        System.out.println("1=>Show unready Fast Foods 2=>Show ready Fast Foods 0=>Back");
        int option = scannerInt.nextInt();
        switch (option) {
            case 1:
                showUnreadyFastFoods(cheef);
                break;
            case 2:
                readyFastFoods(cheef);
                break;
            case 0:
                return;
            default:
                System.out.println("Wrong!!!");

        }

        showCheefMenu(cheef);


    }

    @Override
    public void showUnreadyFastFoods(User cheef) {
        for (Order order : DataBase.orderList) {
            if (order.getStatus().equals(Status.UNREADY)) {
                for (Cart cart : order.getClientCart()) {
                    if (cart.getFood().getType().equals(Type.FAST_FOOD)) {
                        System.out.println("Client id: " + order.getClient().getId() + " name: " + order.getClient().getFullName() + " order products" + order.getClientCart());
                        isFound = true;
                    }
                }

            }
        }
        if (isFound) {
            System.out.println("Enter Client id to select: (0=back)");
            int inputClientId = scannerInt.nextInt();
            if (inputClientId == 0) return;
            for (Order order : DataBase.orderList) {
                if (order.getClient().getId() == inputClientId) {
                    order.setStatus(Status.READY);
                    order.setChief(cheef);
                    System.out.println("Order process: READY");

                }
            }

        } else System.out.println("order not found!");


    }

    @Override
    public void readyFastFoods(User cheef) {
        if (isFound) {
            for (Order order : DataBase.orderList) {
                if (order.getStatus().equals(Status.READY)) {
                    for (Cart cart : order.getClientCart()) {
                        if (cart.getFood().getType().equals(Type.FAST_FOOD)) {
                            System.out.println("Client id: " + order.getClient().getId() + " name: " + order.getClient().getFullName() + " order products: " + order.getClientCart());
                        }
                    }

                }
            }
        } else System.out.println("order not found!");
    }
}
