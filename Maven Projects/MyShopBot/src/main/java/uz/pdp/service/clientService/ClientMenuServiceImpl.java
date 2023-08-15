package uz.pdp.service.clientService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uz.pdp.model.OrderHistory;
import uz.pdp.model.OrderItem;
import uz.pdp.model.abs.User;
import uz.pdp.service.clientService.interfaces.ClientMenuService;
import uz.pdp.service.clientService.interfaces.ClientMyCartService;
import uz.pdp.utils.Util;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;

import static uz.pdp.utils.Db.orderHistoryList;
import static uz.pdp.utils.Util.scnInt;

public class ClientMenuServiceImpl implements ClientMenuService {
    ClientSeeClothesMenuImpl clientSeeClothesMenu = new ClientSeeClothesMenuImpl();
    ClientMyCartService clientMyCartService = new ClientMyCartServiceImpl();
    FilterClothesServiceImpl filterClothesService = new FilterClothesServiceImpl();

    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public void clientMenu(User customer) {
        System.out.print("1.See Clothes\n2.Filter Clothes\n3.My Cart\n4.Show Order History\n5.Fill Balance\n6.Show Balance\n7.Log Out\n=>");
        int option = scannerInt.nextInt();
        switch (option){
            case 1:
                clientSeeClothesMenu.seeClothesMenu(customer);
                break;
            case 2:
                filterClothesService.filterClothesMenu();
                break;
            case 3:
                myCart(customer);
                break;
            case 4:
                clientOrderHistory(customer);
                break;
            case 5:
                fillBalance(customer);
                break;
            case 6:
                showBalance(customer);
                break;

            case 7: return;
            default:
                System.err.println("Wrong option!!!!");
                break;
        }

        clientMenu(customer);
    }

    @Override
    public void seeClothes(User customer) {
        clientSeeClothesMenu.seeClothesMenu(customer);
    }

    @Override
    public void myCart(User customer) {
        clientMyCartService.clientMyCartMenu(customer);
    }

    @Override
    public void filterClothes(User customer) {

    }

    @Override
    public void clientOrderHistory(User customer) {

        long count = orderHistoryList.stream().filter(orderHistory -> orderHistory.getCustomer().getUsername().equals(customer.getUsername())).count();
        if (count == 0){
            System.err.println("No purchases yet!!!");
        }

        orderHistoryList.stream().filter(orderHistory -> orderHistory.getCustomer().getUsername().equals(customer.getUsername()))
                .forEach(System.out::println);

        System.out.println("1=> Extract to excel File 2=> Back");
        int option = scnInt();
        switch (option){
            case 1: {
                Gson gson = new Gson();
                File file = new File("src/main/resources/OrderHistoryForCustomer.xlsx");
                try (Reader reader = new FileReader("src/main/resources/jsons/orderHistoryList.json");
                     OutputStream outputStream = new FileOutputStream(file)) {

                   // List<OrderHistory> orderHistoryList = gson.fromJson(reader, new TypeToken<List<OrderHistory>>(){}.getType());
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
                        if (orderHistoryList.get(i).getCustomer().getUsername().equals(customer.getUsername())){
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
                    }


                    workbook.write(outputStream);
                    workbook.close();

                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }break;

            case 2: return;

            default:
                System.out.println("Wrong option!!!");
                break;
        }
    }

    @Override
    public void fillBalance(User customer) {
        System.out.println("Enter amount: ");
        double amount = scannerInt.nextDouble();

        customer.setBalance(customer.getBalance() + amount);
        Util.print(Util.GREEN, "Successfully Done");
    }

    @Override
    public void showBalance(User customer) {
        System.out.println("Balance: " + customer.getBalance());
    }
}
