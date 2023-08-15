package uz.pdp.service.supplierService;

import uz.pdp.DataBase;
import uz.pdp.enums.Status;
import uz.pdp.model.Order;
import uz.pdp.model.User;

import java.util.Scanner;

public class SupplierServiceImpl implements SupplierService {
    public static boolean isFound = false;
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    @Override
    public void supplierMenu(User supplier) {

        System.out.println("1=>show ready foods\n2=>show on road products\n3=>show received products\n0=>Back");
        int option = scannerInt.nextInt();
        switch (option) {
            case 1:
                showReadyFoods(supplier);
                break;
            case 2:
                showOnRoadProducts(supplier);
                break;
            case 3:
                showReceivedProducts(supplier);
                break;
            case 0:
                return;
            default:
                System.out.println("wrong!");


        }
        supplierMenu(supplier);


    }

    @Override
    public void showReadyFoods(User supplier) {


        //order.getSupplier().getId()== supplier.getId()
        for (Order order : DataBase.orderList) {
            if (order.getStatus().equals(Status.READY)) {
                isFound = true;
                System.out.println("id: " + order.getClient().getId() + " name: " + order.getClient().getFullName());
            }
        }
        if (isFound) {


            System.out.println("Enter Client id to select: (0=>Back)");
            int inputClientId = scannerInt.nextInt();
            if (inputClientId == 0) return;


            for (Order order : DataBase.orderList) {
                if (order.getClient().getId() == inputClientId) {
                    order.setStatus(Status.ON_ROAD);
                    order.setSupplier(supplier);
                    System.out.println("Order process: On road!!!");

                }
            }
        } else System.out.println("No order!!!");


    }

    @Override
    public void showOnRoadProducts(User supplier) {
        for (Order order : DataBase.orderList) {
            if (order.getStatus().equals(Status.ON_ROAD)) {
                System.out.println(order);
                System.out.println(order.getClient().getId());
                isFound=true;
            }
        }
        if (isFound) {
            System.out.println("Enter Client id to select: (0=>Back)");
            int inputClientId = scannerInt.nextInt();
            if (inputClientId == 0) return;

            for (Order order : DataBase.orderList) {
                if (order.getClient().getId() == inputClientId) {
                    order.setStatus(Status.AT_DESTINATION);
                    order.setSupplier(supplier);
                    System.out.println("Order process: AT_DESTINATION!!!");
                }
            }
        }else System.out.println("No order");

    }

    @Override
    public void showReceivedProducts(User supplier) {
        for (Order order : DataBase.orderList) {
            if (order.getSupplier() != null){
                if (order.getSupplier().getId() == supplier.getId() && order.getStatus() == Status.RECEIVED){
                    System.out.println(order);
                }

            }
        }
    }
}
