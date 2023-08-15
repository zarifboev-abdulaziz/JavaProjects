package uz.pdp.service.adminService.crud;

import uz.pdp.service.adminService.crud.interfaces.ProductService;

import java.util.Scanner;

public class ProductServiceImpl implements ProductService {
    @Override
    public void productMenu() {
        DrinkServiceImpl drinkService = new DrinkServiceImpl();
        FastFoodServiceImpl fastFoodService = new FastFoodServiceImpl();
        DesertServiceImpl desertService = new DesertServiceImpl();

        System.out.print("[1] Fast_Foods menu | [2] Drinks menu | [3] Deserts menu | [4] Back \n-> ");
        int option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1:
                fastFoodService.fastFoodMenu();
                break;
            case 2:
                drinkService.drinkMenu();
                break;
            case 3:
                desertService.desertMenu();
                break;
            case 4:
                return;
            default:
                System.out.println("Wrong option!!! Please, try again!");
                break;
        }
        productMenu();
    }
}
