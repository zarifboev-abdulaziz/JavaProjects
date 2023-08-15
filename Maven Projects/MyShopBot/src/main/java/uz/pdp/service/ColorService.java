package uz.pdp.service;

import uz.pdp.model.Color;
import uz.pdp.service.crud.CrudRepository;
import uz.pdp.utils.DataFromJson;
import uz.pdp.utils.Db;
import uz.pdp.utils.Util;

import java.util.Scanner;

import static uz.pdp.utils.Util.CYAN;
import static uz.pdp.utils.Util.GREEN;

public class ColorService implements CrudRepository {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    @Override
    public void menu() {
        Util.print(CYAN,"=== Color menu ===");
        Util.print(CYAN,"1=> Add Color 2=> Show Color list 3=> update Color 4=> delete Color 5=> Back");
        int option = scannerInt.nextInt();
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
                break;

            case 5: return;
            default:
                Util.print(Util.RED,"Wrong option!");
                break;
        }

    }

    @Override
    public void create() {
        Util.print(Util.GREEN,"color name: ");
        String colorName = scannerStr.nextLine();

        Color color = new Color(colorName);
        Db.colorList.add(color);
        Util.print(CYAN,"Successfully created!!!");

        DataFromJson.updateJson();
    }

    @Override
    public void read() {
        System.out.println("=== Color list ===");
        for (Color color : Db.colorList) {
            System.out.println(color);
        }
    }

    @Override
    public void update() {
        read();

        Util.print(CYAN, "Enter new color name");
        String colorName = scannerStr.nextLine();

        Color selectedColor = null;
        for (Color color : Db.colorList) {
            if(color.getName().equalsIgnoreCase(colorName)){
                selectedColor = color;
            }
        }

        if(selectedColor != null){
            System.out.println("Already exist");
        }else {
            Color color = new Color();
            assert false;
            color.setName(selectedColor.getName());
        }

        DataFromJson.updateJson();
    }

    @Override
    public void delete() {
        read();

        Util.print(GREEN,"Enter color name: ");
        String colorName = scannerStr.nextLine();

        Color selectedColor = null;
        for (Color color : Db.colorList) {
            if(color.getName().equalsIgnoreCase(colorName)){
                selectedColor = color;
                Db.colorList.remove(selectedColor);
            }
        }

        DataFromJson.updateJson();
        Util.print(GREEN,"Successfully deleted!!!");
    }
}
