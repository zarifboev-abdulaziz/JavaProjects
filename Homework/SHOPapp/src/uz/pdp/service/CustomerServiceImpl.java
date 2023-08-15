package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.Cloth;
import uz.pdp.model.User;
import uz.pdp.model.enums.Size;
import uz.pdp.model.enums.Status;
import uz.pdp.service.interfaces.CustomerService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerServiceImpl implements CustomerService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public void customerMenu(User user) {
        while (true){
            System.out.println("1.Buy Cloth\n2.My Clothes\n3.Average price\n4.average price by Size\n5.show Balance\n6.Fill balance\n7.My cart\n8.Log out");
            int option = scannerInt.nextInt();
            switch (option){
                case 1:
                    buyCloth(user);
                    break;
                case 2:
                    myClothes(user);
                    break;
                case 3:
                    averagePrice(user);
                    break;
                case 4:
                    averagePriceBySize(user);
                    break;
                case 5:
                    showBalance(user);
                    break;
                case 6:
                    fillBalance(user);
                    break;

                case 7:

                    return;
                default:
                    System.out.println("Wrong option");
                    break;
            }

        }
    }

    @Override
    public void buyCloth(User user) {
        for (Cloth cloth : Store.clothList) {
            if (cloth.getStatus() == Status.ONSALE){
                System.out.println(cloth);
            }
        }

        System.out.println("Enter cloth id to buy: ");
        int inputId = 0;
        try {
            inputId = scannerInt.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Wrong id!!!");
        }


        boolean isFound = false;
        for (Cloth cloth : Store.clothList) {
            if(cloth.getId() == inputId){
                isFound = true;

                //transaction process
                if (user.getBalance() < cloth.getPrice()){
                    System.out.println("You do not have enough money to buy this cloth!!!");
                    return;
                }
                user.setBalance(user.getBalance() - cloth.getPrice());
                for (User user1 : Store.userList) {
                    if(user1.getId() == cloth.getOwnerId()){
                        user1.setBalance(user1.getBalance() + cloth.getPrice());
                    }
                }
                //Transaction process done!!!

                cloth.setOwnerId(user.getId());
                cloth.setStatus(Status.SOLD);
                break;
            }
        }

        if (isFound){
            System.out.println("Sotib olindi!!!");
        }else {
            System.out.println("Cloth not found!!!");
        }


    }

    @Override
    public void myClothes(User user) {
        int count = 0;

        for (Cloth cloth : Store.clothList) {
            if (cloth.getOwnerId() == user.getId()){
                count++;
                System.out.println(cloth);
            }
        }

        if (count == 0){
            System.out.println("Kiyimim yo'q!!!");
            return;
        }
    }

    @Override
    public void averagePrice(User user) {
        int count = 0;
        int sumPrice = 0;

        for (Cloth cloth : Store.clothList) {
            if(user.getId() == cloth.getOwnerId()){
                count++;
                sumPrice += cloth.getPrice();
            }
        }

        try {
            System.out.println("Average Price: " + (sumPrice/count));
        }catch (ArithmeticException e){
            System.out.println("You didnt buy cloth!!!");
        }

    }

    @Override
    public void averagePriceBySize(User user) {
        int count = 0;
        int sumPrice = 0;
        Size size = null;

        System.out.println("Enter size of clothes: ");
        String inputSize = scannerStr.nextLine();

        try {
           size = Size.valueOf(inputSize);
        }catch (IllegalArgumentException e){
            System.out.println("wrong size input!!!");
        }

        for (Cloth cloth : Store.clothList) {
            if(cloth.getOwnerId() == user.getId()){
                if(cloth.getSize() == size){
                    count++;
                    sumPrice += cloth.getPrice();
                }
            }
        }

        try {
            System.out.println("Average price " + (sumPrice/count));
        }catch (ArithmeticException e){
            System.out.println("List is empty!!!");
        }

    }

    @Override
    public void showBalance(User user) {
        System.out.println(user.getBalance());
    }

    @Override
    public void fillBalance(User user) {
        System.out.println("Enter amount: ");
        double amount = scannerInt.nextDouble();

        if(amount < 0){
            System.out.println("Invalid amount!!!");
            return;
        }

        user.setBalance(user.getBalance() + amount);
        System.out.println("Successfully done!!!");

    }
}
