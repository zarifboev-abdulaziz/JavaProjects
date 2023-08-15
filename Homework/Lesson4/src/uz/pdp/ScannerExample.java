package uz.pdp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerExample {
    public static void main(String[] args) {
        File file = new File("myFolder/folderOne/Test.txt");

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNext()){
                System.out.println(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}
