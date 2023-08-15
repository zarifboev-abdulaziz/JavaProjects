package uz.pdp.workingWithMSFiles;

import com.google.gson.Gson;
import org.apache.poi.xwpf.usermodel.*;
import uz.pdp.model.Car;

import java.io.*;

public class WordFileExample {
    public static void main(String[] args) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader("src/main/resources/carList.json");
             FileOutputStream outputStream = new FileOutputStream("src/main/resources/Test.docx")) {

            Car[] cars = gson.fromJson(reader, Car[].class);

             XWPFDocument document = new XWPFDocument();
             XWPFParagraph paragraph = document.createParagraph();
             XWPFRun run = paragraph.createRun();

             run.setText("Heello brrrrr");

            XWPFTable table = document.createTable();

            XWPFTableRow row = table.getRow(0);
            row.getCell(0).setText("T/R");
            row.addNewTableCell().setText("Car brand");
            row.addNewTableCell().setText("Car model");

            for (int i = 0; i < cars.length; i++) {
                Car car = cars[i];
                XWPFTableRow row1 = table.createRow();

                row1.getCell(0).setText(String.valueOf(i+1));
                row1.getCell(1).setText(car.getModel());
                row1.getCell(2).setText(car.getBrand());
            }


             document.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
