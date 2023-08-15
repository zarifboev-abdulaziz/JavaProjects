package uz.pdp.service.adminService.crud;

import uz.pdp.DataBase;
import uz.pdp.enums.Type;
import uz.pdp.model.Food;
import uz.pdp.service.adminService.crud.interfaces.FastFoodService;

import java.util.Scanner;

public class FastFoodServiceImpl implements FastFoodService {
    @Override
    public void fastFoodMenu() {
        System.out.println("[1] Show Fast_Foods | [2] Add Fast_Food | [3] Update Fast_Food | [4] Delete Fast_Food | [5] Back \n-> ");
        int option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1:
                showFastFoodList();
                break;
            case 2:
                addFastFood();
                break;
            case 3:
                upDateFastFood();
                break;
            case 4:
                deleteFastFood();
                break;
            case 5:
                return;
            default:
                System.out.println("Wrong option!!! Please, try again!");
                break;
        }
        fastFoodMenu();
    }

    @Override
    public void showFastFoodList() {
        DataBase.foodList.stream().filter(food -> food.getType().equals(Type.FAST_FOOD)).forEach(System.out::println);

    }

    @Override
    public void addFastFood() {
        showFastFoodList();
        System.out.print("\nEnter Fast_Food name: ");
        String name = new Scanner(System.in).nextLine();

        for (Food food : DataBase.foodList) {
            if (food.getName().equalsIgnoreCase(name)) {
                System.out.println(food.getName() + " already exists!!!");
                return;
            }
        }
        System.out.print("Enter Fast_Food code (unique): ");
        int productCode = new Scanner(System.in).nextInt();
        System.out.print("Enter Fast_Food price: ");
        double price = new Scanner(System.in).nextDouble();

        Food food = new Food(productCode, name, price, Type.DRINK);
        DataBase.foodList.add(food);
        System.out.println(food.getName() + " successfully added!!!");
    }

    @Override
    public void upDateFastFood() {
        showFastFoodList();
        System.out.print("\nEnter Fast_Food name: ");
        String name = new Scanner(System.in).nextLine();

        for (Food food : DataBase.foodList) {
            if (food.getName().equalsIgnoreCase(name)) {
                System.out.print("\nEnter new name of Fast_Food: ");
                String newName = new Scanner(System.in).nextLine();
                System.out.print("Enter new new code (unique) of Fast_Food: ");
                int productCode = new Scanner(System.in).nextInt();
                System.out.print("Enter new price of Fast_Food: ");
                double price = new Scanner(System.in).nextDouble();

                Food newFood = new Food(productCode, newName, price, Type.FAST_FOOD);
                DataBase.foodList.add(newFood);
                System.out.println(food.getName() + " successfully updated!!!");
                DataBase.foodList.remove(food);
                return;
            }
        }
        System.out.println("Not found Fast_Food!!! Please, try again!");
    }

    @Override
    public void deleteFastFood() {
        showFastFoodList();
        System.out.print("\nEnter Fast_Food name: ");
        String name = new Scanner(System.in).nextLine();

        for (Food food : DataBase.foodList) {
            if (food.getName().equalsIgnoreCase(name)) {
                System.out.println(food.getName() + " successfully deleted!!!");
                DataBase.foodList.remove(food);
                return;
            }
        }

        System.out.print("Not found Fast_Food!!! Please, try again!\n");
    }
}
