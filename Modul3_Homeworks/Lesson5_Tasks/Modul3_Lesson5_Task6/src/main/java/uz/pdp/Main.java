package uz.pdp;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("Enter pathName: ");
        String pathName = new Scanner(System.in).nextLine();

        String fileFormat = "";

        //checking fileFormat
        for (int i = 0; i < pathName.length(); i++) {
            if (pathName.charAt(i) == '.'){
                fileFormat = pathName.substring(i);
            }
        }

        File file = new File(pathName);

        try (Reader reader = new FileReader(file)) {

            char[] chars = new char[(int)file.length()];
            reader.read(chars);


            //Creating file to copy
            File copyFile = new File("src/main/resources/CopiedFile" + fileFormat);

            Writer writer = new FileWriter(copyFile);

            writer.write(chars);

            writer.close();

            //Opening file on the screen
            Desktop desktop = Desktop.getDesktop();
            desktop.open(copyFile);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
