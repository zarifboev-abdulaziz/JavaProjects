package uz.pdp;

import java.io.*;

public class ReaderExample {
    public static void main(String[] args) {
        File file = new File("History.txt");

        try {
            System.out.println(file.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Writer writer = new FileWriter(file, true)) {

            String data = "This data should be written in the history\n";

            writer.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
