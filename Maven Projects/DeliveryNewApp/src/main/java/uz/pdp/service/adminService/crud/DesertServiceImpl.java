package uz.pdp.service.adminService.crud;

import uz.pdp.DataBase;
import uz.pdp.enums.Type;
import uz.pdp.model.Food;
import uz.pdp.service.adminService.crud.interfaces.DesertService;

import java.util.Scanner;

public class DesertServiceImpl implements DesertService {
    @Override
    public void desertMenu() {
        System.out.println("[1] Show deserts | [2] Add desert | [3] Update desert | [4] Delete desert | [5] Back \n-> ");
        int option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1:
                showDesertList();
                break;
            case 2:
                addDesert();
                break;
            case 3:
                upDateDesert();
                break;
            case 4:
                deleteDesert();
                break;
            case 5:
                return;
            default:
                System.out.println("Wrong option!!! Please, try again!");
                break;
        }
        desertMenu();
    }

    @Override
    public void showDesertList() {
        DataBase.foodList.stream().filter(food -> food.getType().equals(Type.DESERT)).forEach(System.out::println);
    }

    @Override
    public void addDesert() {
        showDesertList();
        System.out.print("\nEnter desert name: ");
        String name = new Scanner(System.in).nextLine();

        for (Food food : DataBase.foodList) {
            if (food.getName().equalsIgnoreCase(name)) {
                System.out.println(food.getName() + " already exists!!!");
                return;
            }
        }
        System.out.print("Enter desert code (unique): ");
        int productCode = new Scanner(System.in).nextInt();
        System.out.print("Enter desert price: ");
        double price = new Scanner(System.in).nextDouble();

        Food food = new Food(productCode, name, price, Type.DESERT);
        DataBase.foodList.add(food);
        System.out.println(food.getName() + " successfully added!!!");
    }

    @Override
    public void upDateDesert() {
        showDesertList();
        System.out.print("\nEnter desert name: ");
        String name = new Scanner(System.in).nextLine();

        for (Food food : DataBase.foodList) {
            if (food.getName().equalsIgnoreCase(name)) {
                System.out.print("\nEnter new name of desert: ");
                String newName = new Scanner(System.in).nextLine();
                System.out.print("Enter new new code (unique) of desert: ");
                int productCode = new Scanner(System.in).nextInt();
                System.out.print("Enter new price of desert: ");
                double price = new Scanner(System.in).nextDouble();

                Food newFood = new Food(productCode, newName, price, Type.DESERT);
                DataBase.foodList.add(newFood);
                System.out.println(food.getName() + " successfully updated!!!");
                DataBase.foodList.remove(food);
                return;
            }
        }
        System.out.println("Not found desert!!! Please, try again!");
    }

    @Override
    public void deleteDesert() {
        showDesertList();
        System.out.print("\nEnter desert name: ");
        String name = new Scanner(System.in).nextLine();

        for (Food food : DataBase.foodList) {
            if (food.getName().equalsIgnoreCase(name)) {
                System.out.println(food.getName() + " successfully deleted!!!");
                DataBase.foodList.remove(food);
                return;
            }
        }

        System.out.print("Not found desert!!! Please, try again!\n");

    }
}
