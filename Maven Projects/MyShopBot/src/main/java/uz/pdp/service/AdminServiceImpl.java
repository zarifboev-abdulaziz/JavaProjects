package uz.pdp.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uz.pdp.model.OrderHistory;
import uz.pdp.model.OrderItem;
import uz.pdp.service.interfaces.AdminService;
import uz.pdp.utils.Util;

import java.awt.*;
import java.io.*;
import java.util.List;

import static uz.pdp.utils.Db.orderHistoryList;
import static uz.pdp.utils.Util.scnInt;


public class AdminServiceImpl implements AdminService {
    ClothService clothService = new ClothService();
    ColorService colorService = new ColorService();
    PayTypeService payTypeService = new PayTypeService();


    @Override
    public void showAdminMenu() {
        Util.print(Util.RED_BACKGROUND,"===Admin Menu===");
        Util.print(Util.CYAN,"1.Cloth menu\n2.Color menu\n3.My client menu\n4.PayType menu\n5.Transaction history\n6.Log out");
        int option = Util.scannerStr.nextInt();
        switch (option){
            case 1:
                Util.print(Util.CYAN,"=== Cloth menu ===");
                try {
                    clothService.menu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                Util.print(Util.CYAN,"=== Color menu ===");
                colorService.menu();
                break;
            case 3:
                Util.print(Util.CYAN,"=== My client menu ===");
                System.out.println("Under construction!!!");
                break;
            case 4:
                Util.print(Util.CYAN,"=== Pay type menu ===");
                try {
                    payTypeService.menu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                Util.print(Util.CYAN,"=== Transaction history ===");
               showOrderMenu();
                break;
            case 6:
                return;
            default:
                Util.print(Util.RED,"Wrong option!!!");
        }
        showAdminMenu();
    }

    private void showOrderMenu() {

            System.out.println("1=> Show order History 2=> Extract to excel File");
            int option = scnInt();
            switch (option){
                case 1:
                    for (OrderHistory orderHistory : orderHistoryList) {
                        System.out.println(orderHistory);
                    }
                    break;
                case 2: {
                    Gson gson = new Gson();
                    File file = new File("src/main/resources/OrderHistoryForCustomer.xlsx");
                    try (Reader reader = new FileReader("src/main/resources/jsons/orderHistoryList.json");
                         OutputStream outputStream = new FileOutputStream(file)) {

                        List<OrderHistory> orderHistoryList = gson.fromJson(reader, new TypeToken<List<OrderHistory>>(){}.getType());

                        XSSFWorkbook workbook = new XSSFWorkbook();
                        XSSFSheet spreadSheet = workbook.createSheet();
                        XSSFSheet sheet = workbook.createSheet();
                        XSSFRow row = spreadSheet.createRow(0);

                        row.createCell(0).setCellValue("customer");
                        row.createCell(1).setCellValue("items");
                        row.createCell(2).setCellValue("price");
                        row.createCell(3).setCellValue("commissionFeeSum");
                        row.createCell(4).setCellValue("payType");


                        for (int i = 0; i < orderHistoryList.size(); i++) {
                                OrderHistory orderHistory = orderHistoryList.get(i);

                                String items = "";
                                for (OrderItem item : orderHistory.getItems()) {
                                    items += item.getCloth().getName() + " ";
                                }

                                row = spreadSheet.createRow(i+1);
                                row.createCell(0).setCellValue(orderHistory.getCustomer().getFullName());
                                row.createCell(1).setCellValue(items);
                                row.createCell(2).setCellValue(orderHistory.getPrice());
                                row.createCell(3).setCellValue(orderHistory.getCommissionFeeSum());
                                row.createCell(4).setCellValue(orderHistory.getPayType().getName());

                        }


                        workbook.write(outputStream);
                        workbook.close();

                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(file);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
    }
}
