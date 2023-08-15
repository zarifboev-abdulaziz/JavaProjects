package uz.pdp;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Enter pathName: ");
        String pathName = new Scanner(System.in).nextLine();

        countLines(pathName);





    }

    public static void countLines(String pathName){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName))) {

            String line = bufferedReader.readLine();

            int count = 0;
            while (line != null){
                count++;
                line = bufferedReader.readLine();
            }

            System.out.println("Berilgan matnda " + count + " ta qator bor!!!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
