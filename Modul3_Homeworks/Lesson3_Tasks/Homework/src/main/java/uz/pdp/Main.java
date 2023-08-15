package uz.pdp;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter pathName: ");
        String pathName = scanner.nextLine();

        checkPathName(pathName);

    }

    public static void checkPathName(String pathName){
        File file = new File(pathName);

        if (file.isDirectory()){ //papkami??
            System.out.println("Berilgan pathname papka!");
        }else if (file.isFile()){ //faylmi??
            System.out.println("Berilgan pathname fayl!");
        }else {
            System.out.println("Berilgan pathname papka ham fayl ham emas");
        }
    }

}
