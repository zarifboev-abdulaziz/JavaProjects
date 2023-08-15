package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.Cloth;
import uz.pdp.model.User;
import uz.pdp.model.enums.Size;
import uz.pdp.model.enums.Status;
import uz.pdp.service.interfaces.ClothService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClothServiceImpl implements ClothService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public void clothMenu(User user) {
        while (true){
            System.out.println("1.showMyClothes\n2.addCloth\n3.updateCloth\n4.deleteCloth\n5.Back");
            int option = scannerInt.nextInt();
            switch (option){
                case 1:
                    showMyClothes(user);
                    break;
                case 2:
                    addCloth(user);
                    break;
                case 3:
                    updateCloth(user);
                    break;
                case 4:
                    deleteCloth(user);
                    break;
                case 5: return;
                default:
                    System.out.println("wrong option");
                    break;
            }
        }
    }

    @Override
    public void showMyClothes(User user) {
        int count = 0;

        for (Cloth cloth : Store.clothList) {
            if(user.getId() == cloth.getOwnerId()){
                count++;
                System.out.println(cloth);
            }
        }

        if (count == 0){
            System.out.println("Hali kiyimni sotuvga chiqarmadingiz!!!");
            return;
        }
    }

    @Override
    public void addCloth(User user) {
        Size karobka = null;

        int id = (int)(Math.random()*10000);

        System.out.println("Enter cloth name: ");
        String name = scannerStr.nextLine();

        System.out.println("Enter cloth color: ");
        String color = scannerStr.nextLine();

        System.out.print("Mavjud O'lchamlar: ");
        for (Size value : Size.values()) {
            System.out.print(value + "   ");
        }
        System.out.println();

        System.out.println("Enter cloth Size: ");
        String inputSize = scannerStr.nextLine();


        try {
            karobka = Size.valueOf(inputSize);
        }catch (IllegalArgumentException e){
            System.out.println("Wrong ID: ");
        }catch (Exception e){
            System.out.println("Xatolik mavjud!!!");
        }

        System.out.println("Enter cloth price: ");
        double price = scannerInt.nextDouble();

        Cloth cloth = new Cloth(id, name, user.getId(), color, karobka, price, Status.ONSALE);
        Store.clothList.add(cloth);
        System.out.println("SUCCESS!!!");

    }

    @Override
    public void updateCloth(User user) {
        int count = 0;

        for (Cloth cloth : Store.clothList) {
            if(user.getId() == cloth.getOwnerId()){
                count++;
                System.out.println(cloth);
            }
        }

        if (count == 0){
            System.out.println("Hali kiyim yo'q!!!");
            return;
        }

        System.out.println("Enter cloth id to delete: ");
        int inputId = 0;

        try {
            inputId = scannerInt.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Wrong id");
            return;
        }catch (Exception e){
            System.out.println("Xatolik bor");
            return;
        }


        for (Cloth cloth : Store.clothList) {
            if(cloth.getId() == inputId){
                System.out.println("Enter new Price: ");
                double price = scannerInt.nextDouble();

                cloth.setPrice(price);
                System.out.println("Successfully updated!!");
                return;
            }
        }

    }

    @Override
    public void deleteCloth(User user) {
        int count = 0;

        for (Cloth cloth : Store.clothList) {
            if(user.getId() == cloth.getOwnerId()){
                count++;
                System.out.println(cloth);
            }
        }

        if (count == 0){
            System.out.println("Hali kiyim yo'q!!!");
            return;
        }

        System.out.println("Enter cloth id to delete: ");
        int inputId = 0;

        try {
            inputId = scannerInt.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Wrong id");
            return;
        }catch (Exception e){
            System.out.println("Xatolik bor");
            return;
        }


        for (Cloth cloth : Store.clothList) {
            if(cloth.getId() == inputId){
                Store.clothList.remove(cloth);
                System.out.println("Success");
                break;
            }
        }


    }
}
