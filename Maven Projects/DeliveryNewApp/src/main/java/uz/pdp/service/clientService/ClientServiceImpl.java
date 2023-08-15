package uz.pdp.service.clientService;

import uz.pdp.DataBase;
import uz.pdp.enums.Status;
import uz.pdp.enums.Type;
import uz.pdp.model.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ClientServiceImpl implements ClientService {
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);


    @Override
    public void showClientMenu(User user) {
        System.out.println("1.Show foods\n2.My Cart\n3.Show order Status\n4.Receive Food\n5.Purchase History\n6.Show Balance\n7.Fill Balance\n8.Log Out");
        int option = scannerInt.nextInt();
        switch (option){
            case 1:
                showProducts(user);
                break;
            case 2:
                myCart(user);
                break;
            case 3:
                showOrderStatus(user);
                break;
            case 4:
                receivedProduct(user);
                break;
            case 5:
                purchasedHistory(user);
                break;
            case 6:
                showBalance(user);
                break;
            case 7:
                fillBalance(user);
                break;

            case 8:return;
            default:
                System.out.println("Wrong option!!!");
                break;
        }

        showClientMenu(user);

    }

    @Override
    public void myCart(User user) {
        System.out.println("1.Show foods in my cart\n2.Add food\n3.Remove food\n4.Checkout\n5.Back");
        int option = scannerInt.nextInt();
        switch (option){
            case 1:
                showFoodsInClientCart(user);
                break;
            case 2:
                showProducts(user);
                break;
            case 3:
                removeFoodFromCart(user);
                break;
            case 4:
                checkOut(user);
                break;

            case 5:return;
            default:
                System.out.println("Wrong option");
                break;

        }
        myCart(user);
    }

    private void checkOut(User user)  {
        PayType selectedPayType = null;
        double totalPrice = 0;

        System.out.println("========= Cart ========");
        for (Cart cart : user.getMyCart()) {
            System.out.println(cart);
            totalPrice += cart.getFood().getPrice();
        }

        System.out.println("======= Payment Type =======");
        for (PayType payType : DataBase.payTypeList) {
            System.out.println(payType);
        }
        System.out.println("Enter payType name to  select: ");
        String inputName = scannerStr.nextLine();

        for (PayType payType : DataBase.payTypeList) {
            if (payType.getName().equalsIgnoreCase(inputName)){
                selectedPayType = payType;
            }
        }

        double finalPrice = totalPrice * (100 + selectedPayType.getCommissionFee())/100;

        if (user.getBalance() < finalPrice){
            System.err.println("You do not have enough money to check out!!!");
            return;
        }

        user.setBalance(user.getBalance() - finalPrice);
        DataBase.admin.setBalance(DataBase.admin.getBalance() + totalPrice);

        Order order = new Order(user, user.getMyCart(), Status.UNREADY, new User(), new User(), LocalDateTime.now(), totalPrice, selectedPayType);
        DataBase.orderList.add(order);

        System.out.println("Successfully done!!!");

    }

    private void removeFoodFromCart(User user) {

        for (Cart cart : user.getMyCart()) {
            System.out.println(cart);
        }
        System.out.println("Enter food Code to Remove: ");
        int inputCode = scannerInt.nextInt();

        for (Cart cart : user.getMyCart()) {
            if (cart.getFood().getProductCode() == inputCode){
                user.getMyCart().remove(cart);
            }
        }

        System.out.println("Successfully removed!!!");


    }

    private void showFoodsInClientCart(User user) {
        int count = 0;
        for (Cart cart : user.getMyCart()) {
            System.out.println(cart);
            count++;
        }

        if (count == 0){
            System.out.println("Cart is empty!!!");
        }
    }

    @Override
    public void fillBalance(User user) {
        PayType selectedPayType = null;

        System.out.println("Please select pay Type: ");
        for (PayType payType : DataBase.payTypeList) {
            System.out.println(payType);
        }
        System.out.println("Enter pay type name to select: ");
        String name = scannerStr.nextLine();

        for (PayType payType : DataBase.payTypeList) {
            if (payType.getName().equalsIgnoreCase(name)){
                selectedPayType  = payType;
            }
        }

        System.out.println("Enter amount:");
        double amount = scannerInt.nextDouble();

        user.setBalance(user.getBalance() + amount);
        System.out.println("Successfully done");

    }

    @Override
    public void showBalance(User user) {
        System.out.println("Balans: " + user.getBalance());
    }

    @Override
    public void purchasedHistory(User user) {
        for (OrderHistory orderHistory : DataBase.orderHistoryList) {
            if (orderHistory.getClient().getId() == user.getId()) {
                System.out.println(orderHistory);
            }
        }
    }

    @Override
    public void showOrderStatus(User user) {
        for (Order order : DataBase.orderList) {
            if (order.getClient().getId() == user.getId() && order.getStatus() != Status.RECEIVED){
                System.out.println(order);
            }
        }

    }

    @Override
    public void receivedProduct(User user) {
        Order selectedOrder = null;

        for (Order order : DataBase.orderList) {
            if(order.getClient().getId() == user.getId() && order.getStatus() == Status.AT_DESTINATION){
                System.out.println(order);
                selectedOrder = order;
            }
        }

        selectedOrder.setStatus(Status.RECEIVED);
        System.out.println("Successfully Recieved!!!");

        int id = (int)(Math.random()*10000);
        OrderHistory orderHistory = new OrderHistory(id, user, selectedOrder.getClientCart(), selectedOrder.getChief(), selectedOrder.getSupplier(), selectedOrder.getTotalPrice(), selectedOrder.getPayType());
        DataBase.orderHistoryList.add(orderHistory);

    }

    @Override
    public void showProducts(User user) {

        Food selectedFood = null;
        Type foodType = null;

        System.out.println("1.Fast Foods\n2.Drinks\n3.Deserts\n4.Back");
        int option = scannerInt.nextInt();
        switch (option){
            case 1:
                foodType = Type.FAST_FOOD;
                break;
            case 2:
                foodType = Type.DRINK;
                break;
            case 3:
                foodType = Type.DESERT;
                break;
                case 4: return;
            default:
                System.out.println("Wrong option");
                break;
        }

        for (Food food : DataBase.foodList) {
            if (food.getType() == foodType){
                System.out.println(food);
            }
        }

        System.out.println("Enter food Code to add to your cart!");
        int inputCode = scannerInt.nextInt();

        for (Food food : DataBase.foodList) {
            if (food.getProductCode() == inputCode){
                selectedFood = food;
            }
        }

        System.out.println("Enter quantity: ");
        int quantity = scannerInt.nextInt();

        if (user.getMyCart().contains(selectedFood)) {
            for (Cart cart : user.getMyCart()) {
                if (cart.getFood().getProductCode() == selectedFood.getProductCode()){
                    cart.setQuantity(cart.getQuantity() + quantity);
                }
            }
        }else {
            int id = (int)(Math.random()*10000);
            Cart cart = new Cart(id, selectedFood, quantity);
            user.getMyCart().add(cart);
        }

        System.out.println("Successfully added to cart!!!");

        showProducts(user);
    }
}
