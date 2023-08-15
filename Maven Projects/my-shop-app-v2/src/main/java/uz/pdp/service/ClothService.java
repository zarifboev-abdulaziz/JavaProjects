package uz.pdp.service;

import uz.pdp.model.Cloth;
import uz.pdp.model.Color;
import uz.pdp.model.StoreItem;
import uz.pdp.model.StoreItem;
import uz.pdp.model.enums.Size;
import uz.pdp.service.crud.CrudRepository;
import uz.pdp.utils.DataFromJson;
import uz.pdp.utils.Db;
import uz.pdp.utils.Util;

import java.io.IOException;
import java.util.Scanner;

public class ClothService implements CrudRepository{
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);


    @Override
    public void menu() throws IOException {
        Util.print(Util.BLUE, "1=> Add Cloth 2=> Show cloth List 3=> update 4=> delete Cloth 5=> Back");
        int option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1:
                create();
                break;
            case 2:
                read();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
            case 5:
                return;
            default:
                Util.print(Util.RED,"Wrong option!");
                break;
        }
        menu();
    }

    @Override
    public void create() throws IOException {
        int code = (int) (Math.random() * (9999 - 1000)) + 1000;

        Util.print(Util.GREEN,"cloth name: ");
        String clothName = scannerStr.nextLine();

        Util.print(Util.GREEN,"cloth color: ");
        String clothColor = scannerStr.nextLine();

        Util.print(Util.GREEN,"cloth size: ");
        String clothSize = scannerStr.nextLine();

        Util.print(Util.GREEN,"cloth price: ");
        double clothPrice = scannerStr.nextDouble();

        Util.print(Util.GREEN,"cloth discount: ");
        double clothDiscount = scannerStr.nextDouble();

        Util.print(Util.GREEN,"cloth quantity: ");
        int quantity = scannerInt.nextInt();

        Cloth cloth = new Cloth(clothName,code, new Color(clothColor),Size.valueOf(clothSize),clothPrice,clothDiscount);
        Db.clothList.add(cloth);
        StoreItem store = new StoreItem(cloth,quantity);
        Db.store.add(store);
        Util.print(Util.CYAN,"Successfully created!!!");

        DataFromJson.updateJson();
    }

    @Override
    public void read() {
        System.out.println("=== Cloth list ===");
        for (Cloth cloth : Db.clothList) {
            System.out.println(cloth);
        }
    }

    @Override
    public void update() throws IOException {
        read();

        System.out.println("Enter cloth productCode to update: ");
        int code = new Scanner(System.in).nextInt();

        for (Cloth cloth : Db.clothList) {
            if (cloth.getProductCode() == code) {
                System.out.println("Enter new cloth's name: ");
                String newClothName = scannerStr.nextLine();

                System.out.println("Enter new cloth's color: ");
                String newClothColor = scannerStr.nextLine();

                System.out.println("Enter new size: ");
                String newClothSize = scannerStr.nextLine();

                System.out.println("Enter new cloth's price: ");
                double newPrice = scannerInt.nextDouble();

                System.out.println("Enter new cloth's discount: ");
                double newDiscount = scannerInt.nextDouble();

                cloth.setName(newClothName);
                cloth.setColor(new Color(newClothColor));
                cloth.setSize(Size.valueOf(newClothSize));
                cloth.setPrice(newPrice);
                cloth.setDiscount(newDiscount);
            }
        }
        System.out.println("Successfully updated!!!");
        DataFromJson.updateJson();
    }

    @Override
    public void delete() {
        read();

      Util.print(Util.GREEN,"cloth's product code to delete: ");
      int code = new Scanner(System.in).nextInt();

        if (Db.clothList.stream().anyMatch(cloth -> cloth.getProductCode() == code)) {
            Db.clothList.removeIf(cloth -> cloth.getProductCode() == code);
            System.out.println("Successfully deleted!");
        }else {
            System.err.println("Not Found!!!!");
        }
        DataFromJson.updateJson();

    }
}
