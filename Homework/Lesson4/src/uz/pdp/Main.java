package uz.pdp;

import java.io.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    public static void main(String[] args) {

        File newFile = new File("myFolder/folderOne/Test.txt");

        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }



        try (OutputStream outputStream = new FileOutputStream(newFile, true);
             InputStream inputStream = new FileInputStream(newFile);
        ) {

//            String text = "Pdp online jav bootcamp!!!";
//            text = "Hello java";
//
//            outputStream.write(text.getBytes());

            byte[] bytes = new byte[inputStream.available()];

            inputStream.read(bytes);


            for (byte aByte : bytes) {
                System.out.print((char) aByte);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

}
