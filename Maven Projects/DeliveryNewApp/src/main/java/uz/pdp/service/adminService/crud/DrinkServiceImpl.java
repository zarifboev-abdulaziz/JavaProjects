package uz.pdp.service.adminService.crud;

import uz.pdp.DataBase;
import uz.pdp.enums.Type;
import uz.pdp.model.Food;
import uz.pdp.service.adminService.crud.interfaces.DrinkService;

import java.util.Scanner;

public class DrinkServiceImpl implements DrinkService {
    @Override
    public void drinkMenu() {
        System.out.println("[1] Show drinks | [2] Add drink | [3] Update drink | [4] Delete drink | [5] Back \n-> ");
        int option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1:
                showDrinkList();
                break;
            case 2:
                addDrink();
                break;
            case 3:
                upDateDrink();
                break;
            case 4:
                deleteDrink();
                break;
            case 5:
                return;
            default:
                System.out.println("Wrong option!!! Please, try again!");
                break;
        }
        drinkMenu();
    }

    @Override
    public void showDrinkList() {
        DataBase.foodList.stream().filter(food -> food.getType().equals(Type.DRINK)).forEach(System.out::println);
    }

    @Override
    public void addDrink() {
        showDrinkList();
        System.out.print("\nEnter drink name: ");
        String name = new Scanner(System.in).nextLine();

        for (Food food : DataBase.foodList) {
            if (food.getName().equalsIgnoreCase(name)) {
                System.out.println(food.getName() + " already exists!!!");
                return;
            }
        }
        System.out.print("Enter drink code (unique): ");
        int productCode = new Scanner(System.in).nextInt();
        System.out.print("Enter drink price: ");
        double price = new Scanner(System.in).nextDouble();

        Food food = new Food(productCode, name, price, Type.DRINK);
        DataBase.foodList.add(food);
        System.out.println(food.getName() + " successfully added!!!");
    }

    @Override
    public void upDateDrink() {
        showDrinkList();
        System.out.print("\nEnter drink name: ");
        String name = new Scanner(System.in).nextLine();

        for (Food food : DataBase.foodList) {
            if (food.getName().equalsIgnoreCase(name)) {
                System.out.print("\nEnter new name of drink: ");
                String newName = new Scanner(System.in).nextLine();
                System.out.print("Enter new new code (unique) of drink: ");
                int productCode = new Scanner(System.in).nextInt();
                System.out.print("Enter new price of drink: ");
                double price = new Scanner(System.in).nextDouble();

                Food newFood = new Food(productCode, newName, price, Type.DRINK);
                DataBase.foodList.add(newFood);
                System.out.println(food.getName() + " successfully updated!!!");
                DataBase.foodList.remove(food);
                return;
            }
        }
        System.out.println("Not found drink!!! Please, try again!");
    }

    @Override
    public void deleteDrink() {
        showDrinkList();
        System.out.print("\nEnter drink name: ");
        String name = new Scanner(System.in).nextLine();

        for (Food food : DataBase.foodList) {
            if (food.getName().equalsIgnoreCase(name)) {
                System.out.println(food.getName() + " successfully deleted!!!");
                DataBase.foodList.remove(food);
                return;
            }
        }

        System.out.print("Not found drink!!! Please, try again!\n");
    }
}
