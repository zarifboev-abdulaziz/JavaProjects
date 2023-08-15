package uz.pdp;

import java.io.*;

public class BufferedReaderExample {
    public static void main(String[] args) {
        File file = new File("myFolder/folderOne/Test.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String text = reader.readLine();

            while (text != null){
                System.out.println(text);
                text  = reader.readLine();
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
