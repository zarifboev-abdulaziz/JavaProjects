package uz.pdp;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter pathName: ");
        String pathName = scanner.nextLine();

        try {
        checkPathName(pathName);
        }catch (NullPointerException e){
            System.out.println("Fayl yo'li noto'g'ri kiritildi!!!");
        }




    }

    public static void checkPathName(String pathName) throws NullPointerException{
        File file = new File(pathName);
        int countFile = 0;
        int countDirectory = 0;

        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()) countDirectory++;
            if (listFile.isFile()) countFile++;
        }

        if (countDirectory >= 1 && countFile >= 1){
            System.out.println("Papka va fayllar bor");
        } else if (countDirectory >= 1){
            System.out.println("Papkalar bor");
        }else if (countFile >= 1){
            System.out.println("Fayllar bor");
        }else {
            System.out.println("Papka bo'sh");
        }

    }

}
