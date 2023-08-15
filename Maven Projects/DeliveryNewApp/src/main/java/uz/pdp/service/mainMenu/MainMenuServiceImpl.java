package uz.pdp.service.mainMenu;

import uz.pdp.DataBase;
import uz.pdp.enums.Type;
import uz.pdp.model.Cart;
import uz.pdp.model.Food;
import uz.pdp.model.User;
import uz.pdp.service.adminService.AdminServiceImpl;
import uz.pdp.service.authService.AuthServiceImpl;
import uz.pdp.service.cheefService.CheefServiceInterfaceImpl;
import uz.pdp.service.clientService.ClientServiceImpl;
import uz.pdp.service.supplierService.SupplierServiceImpl;

import java.util.Scanner;

public class MainMenuServiceImpl implements MainMenuService {
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);
    AuthServiceImpl authService = new AuthServiceImpl();
    AdminServiceImpl adminService = new AdminServiceImpl();
    ClientServiceImpl clientService = new ClientServiceImpl();
    CheefServiceInterfaceImpl cheefServiceInterface = new CheefServiceInterfaceImpl();
    SupplierServiceImpl supplierService = new SupplierServiceImpl();

    @Override
    public void mainMenu() {
        System.out.println("1.Show foods\n2.My cart\n3.register\n4.log in\n5.exit");
        int option = scannerInt.nextInt();
        switch (option){
            case 1:
                showProducts();
                break;
            case 2:
                myCart();
                break;

            case 3:
                authService.register();
                break;
            case 4:
                User user = authService.logIn();
                if (user != null){

                    switch (user.getRole()){
                        case ADMIN:
                            adminService.adminMenu(user);
                            break;
                        case CHIEF:
                            cheefServiceInterface.showCheefMenu(user);
                            break;
                        case CLIENT:
                            user.getMyCart().addAll(DataBase.temporaryCart);
                            DataBase.temporaryCart.removeAll(DataBase.temporaryCart);
                            clientService.showClientMenu(user);
                            break;
                        case SUPPLIER:
                            supplierService.supplierMenu(user);
                            break;
                    }


                }else {
                    System.out.println("User not Found!!!");
                }
                break;

            case 5: return;
            default:
                System.out.println("Wrong option");
                break;
        }
        mainMenu();
    }

    @Override
    public void showProducts() {

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

        if (DataBase.temporaryCart.contains(selectedFood)) {
                for (Cart cart : DataBase.temporaryCart) {
                    if (cart.getFood().getProductCode() == selectedFood.getProductCode()){
                        cart.setQuantity(cart.getQuantity() + quantity);
                    }
                }
            }else {

            int id = (int)(Math.random()*10000);
            Cart cart = new Cart(id, selectedFood, quantity);
            DataBase.temporaryCart.add(cart);
        }

        System.out.println("Successfully added to cart!!!");

        showProducts();
    }

    @Override
    public void myCart() {
        System.out.println("1.Show foods in my cart\n2.Add food\n3.Remove food\n4.Checkout\n5.Back");
        int option = scannerInt.nextInt();
        switch (option){
            case 1:
                showFoodsInGuestCart();
                break;
            case 2:
                showProducts();
                break;
            case 3:
                removeFoodFromCart();
                break;
            case 4:
                System.out.println("Please register or log in to check out!!!");
                break;
            case 5:return;
            default:
                System.out.println("Wrong option");
                break;

        }
        myCart();
    }

    private void showFoodsInGuestCart() {
        int count = 0;
        for (Cart cart : DataBase.temporaryCart) {
            System.out.println(cart);
            count++;
        }
        if (count == 0){
            System.out.println("Cart is empty!!!");
        }
    }

    private void removeFoodFromCart() {
        for (Cart cart : DataBase.temporaryCart) {
            System.out.println(cart);
        }

        System.out.println("Enter food Code to Remove: ");
        int inputCode = scannerInt.nextInt();

        for (Cart cart : DataBase.temporaryCart) {
            if (cart.getFood().getProductCode() == inputCode){
                DataBase.temporaryCart.remove(cart);
            }
        }

        System.out.println("Successfully removed!!!");


    }

    @Override
    public void register() {

    }

    @Override
    public void login() {

    }
}
