package uz.pdp.workingWithMSFiles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uz.pdp.model.Transaction;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class ExcelExample {
    public static void main(String[] args) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader("src/main/resources/History.json");
             FileOutputStream outputStream = new FileOutputStream("src/main/resources/History.xlsx")) {

            Transaction[] transactionList = gson.fromJson(reader, Transaction[].class);
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet spreadSheet = workbook.createSheet("History");

            XSSFRow row = spreadSheet.createRow(0);

            row.createCell(0).setCellValue("id");
            row.createCell(1).setCellValue("clientId");
            row.createCell(2).setCellValue("clientName");
            row.createCell(3).setCellValue("clothName");
            row.createCell(4).setCellValue("clothQuantity");
            row.createCell(5).setCellValue("clothPrice");
            row.createCell(6).setCellValue("PayTypeName");
            row.createCell(7).setCellValue("LocalDate");

            for (int i = 0; i < transactionList.length; i++) {
                Transaction transaction = transactionList[i];
                row = spreadSheet.createRow(i+1);

                row.createCell(0).setCellValue(transaction.getId());
                row.createCell(1).setCellValue(transaction.getClientId());
                row.createCell(2).setCellValue(transaction.getClientName());
                row.createCell(3).setCellValue(transaction.getClothName());
                row.createCell(4).setCellValue(transaction.getClothQuantity());
                row.createCell(5).setCellValue(transaction.getClothPrice());
                row.createCell(6).setCellValue(transaction.getPayTypeName());
                row.createCell(7).setCellValue(transaction.getLocalDate());

            }


            workbook.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
