package uz.pdp;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("Enter pathName - 1: ");
        String pathName1 = new Scanner(System.in).nextLine();

        System.out.println("Enter pathName - 2: ");
        String pathName2 = new Scanner(System.in).nextLine();

        File file1 = new File(pathName1);
        File file2 = new File(pathName2);
        try (Reader reader1 = new FileReader(file1);
             Reader reader2 = new FileReader(file2)) {

            char[] chars1 = new char[(int)file1.length()];
            reader1.read(chars1);

            char[] chars2 = new char[(int)file2.length()];
            reader2.read(chars2);


            //Creating file 3
            File newFile3 = new File("src/main/resources/File3.txt");

            Writer writer = new FileWriter(newFile3);

            writer.write(chars1);
            writer.write(chars2);

            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
