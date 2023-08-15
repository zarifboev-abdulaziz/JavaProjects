package uz.pdp;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate1 = LocalDate.of(2021, 1, 1);



        //src/main/resources
        File file = new File("C:/" + String.valueOf(localDate1.getYear()));
        file.mkdir();

        try {

            while (localDate1.getYear() < 2022) {


                File file1 = new File(file + "/" + String.valueOf(localDate1.getMonth()));
                file1.mkdir();
                File file2 = new File(file1 + "/" + String.valueOf(localDate1) + ".txt");


                Writer writer = new FileWriter(file2);
                writer.write(String.valueOf(localDateTime));
                writer.close();
                localDate1 = localDate1.plusDays(1);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
