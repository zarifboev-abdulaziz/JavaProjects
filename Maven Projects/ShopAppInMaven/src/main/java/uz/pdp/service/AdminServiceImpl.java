package uz.pdp.service;

import com.google.gson.Gson;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uz.pdp.DataBase;
import uz.pdp.model.Transaction;
import uz.pdp.model.User;
import uz.pdp.service.interfaces.AdminService;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminServiceImpl implements AdminService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    ClothServiceImpl clothService = new ClothServiceImpl();
    ClientServiceForAdminImpl clientServiceForAdmin = new ClientServiceForAdminImpl();
    PayTypeServiceImpl payTypeService = new PayTypeServiceImpl();

    @Override
    public void adminMenu(User user) throws InputMismatchException {
        while (true){
            System.out.println("1.Cloth menu\n2.My Client menu\n3.Pay type menu\n4.Transaction History\n5.Extract history to ExcelFile\n6.Log out");
                int option = scannerInt.nextInt();
                switch (option){
                    case 1:
                        clothService.clothMenu(user);
                        break;
                    case 2:
                        clientServiceForAdmin.clientMenu(user);
                        break;
                    case 3:
                        payTypeService.payTypeMenu(user);
                        break;
                    case 4:
                        transactionHistory();
                        break;
                    case 5:
                        extractExcelFile();
                        break;
                    case 6: return;
                    default:
                        System.out.println("Wrong option");
                        break;
                }

        }
    }

    private void extractExcelFile() {
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

    @Override
    public void transactionHistory() {
        for (Transaction transaction : DataBase.transactionList) {
            System.out.println(transaction);
        }
    }
}
