package uz.pdp.service.clientService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import uz.pdp.model.OrderHistory;
import uz.pdp.model.OrderItem;
import uz.pdp.model.PayType;
import uz.pdp.model.abs.User;
import uz.pdp.service.clientService.interfaces.ClientMyCartService;
import uz.pdp.service.clientService.interfaces.ClientSeeClothesMenu;
import uz.pdp.utils.DataFromJson;
import uz.pdp.utils.Db;
import uz.pdp.utils.Util;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static uz.pdp.utils.Db.orderHistoryList;
import static uz.pdp.utils.Db.payTypeList;


public class ClientMyCartServiceImpl implements ClientMyCartService {
    ClientSeeClothesMenu seeClothesMenu = new ClientSeeClothesMenuImpl();
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public void clientMyCartMenu(User customer) {
        System.out.println("1.Show clothes in My Cart\n2.Check Out\n3.Add more\n4.Remove Item From Cart\n5.Back");
        int option = scannerInt.nextInt();
        switch (option){
            case 1:
                showClothesInCart(customer);
                break;
            case 2:
                clientCheckOut(customer);
                break;
            case 3:
                addMore(customer);
                break;
            case 4:
                removeItemFromCart(customer);
                break;

            case 5:
                return;
            default:
                System.err.println("Wrong option!!!");
                break;
        }
        clientMyCartMenu(customer);

    }

    @Override
    public void showClothesInCart(User customer) {
        Util.print(Util.CYAN, "===============  Clothes  =============");

        customer.getMyCart().forEach(orderItem -> System.out.println(orderItem.getCloth() + ", Quantity: " + orderItem.getQuantity()));

    }

    @Override
    public void addMore(User customer) {
        seeClothesMenu.seeClothesMenu(customer);
    }

    @Override
    public void removeItemFromCart(User customer) {
        if (customer.getMyCart().size() == 0) {
            System.out.println("Cart is Empty!!!");
        }

        Util.print(Util.CYAN, "===============  Clothes  =============");
        customer.getMyCart().forEach(orderItem -> System.out.println(orderItem.getCloth()));

        System.out.println("Enter product Code to remove: ");
        int inputCode = scannerInt.nextInt();

        if (customer.getMyCart().stream().anyMatch(orderItem -> orderItem.getCloth().getProductCode() == inputCode)) {
            customer.getMyCart().removeIf(orderItem -> orderItem.getCloth().getProductCode() == inputCode);
            Util.print(Util.RED, "Removed form Cart!!!");
        }else {
            Util.print(Util.RED, "Cloth not found!!!");
        }

        DataFromJson.updateJson();

    }

    @Override
    public void clientCheckOut(User customer) {
        if (customer.getMyCart().size() == 0){
            System.out.println("Cart is empty!!!");
            return;
        }

        payTypeList.forEach(System.out::println);
        System.out.println("Enter payType name to select: ");
        String inputPayTypeName = scannerInt.toString();

        boolean isFound = false;
        if (payTypeList.stream().anyMatch(payType -> payType.getName().equalsIgnoreCase(inputPayTypeName))) {
            isFound = true;
        }
        if (!isFound){
            System.err.println("PayType not Found!!!");
        }

        PayType selectedPayType = payTypeList.stream()
                .filter(payType -> payType.getName().equalsIgnoreCase(inputPayTypeName))
                .findFirst()
                .orElse(null);

        double totalPrice = 0;
        for (OrderItem orderItem : customer.getMyCart()) {
            System.out.println(orderItem.getCloth().getName());
            totalPrice += (orderItem.getCloth().getPrice()) * (orderItem.getQuantity());
        }

        assert selectedPayType != null;
        double finalPrice = totalPrice * (100 + selectedPayType.getCommissionFee())/100;

        if (customer.getBalance() >= finalPrice) {

            customer.setBalance(customer.getBalance() - finalPrice);
            Db.admin.setBalance(Db.admin.getBalance() + totalPrice);

            System.out.println(" Successfully bought!!!");
            System.out.println("Your balance => " + customer.getBalance());

        } else {
            System.out.println("You don't have enough money!!!");
            return;
        }

                OrderHistory orderHistory = new OrderHistory(customer, customer.getMyCart(), finalPrice, selectedPayType.getCommissionFee(), selectedPayType);
                orderHistoryList.add(orderHistory);
                DataFromJson.updateJson();

                downloadPdf_check(customer, selectedPayType);

        }

    public void downloadPdf_check(User customer, PayType selectedPayType) {
        File file = new File("src/main/resources/test.pdf");
        try (PdfWriter writer = new PdfWriter("src/main/resources/test.pdf")) {
            PdfDocument pdfDocument = new PdfDocument(writer);

            pdfDocument.addNewPage();
            Document document = new Document(pdfDocument);
            Paragraph paragraph = new Paragraph(customer.getFullName()+" 's check paper");
            document.add(paragraph);
            float[] pointColumnWidth = {150F, 150F, 150F,150F, 150F, 150F,150F};
            Table table = new Table(pointColumnWidth);
            table.addCell("T/R");
            table.addCell("Customer's name");
            table.addCell("PayType");
            table.addCell("ClothName");
            table.addCell("Cloth price");
            table.addCell("Cloth quantity");
            table.addCell("Purchase time");
            Gson gson = new Gson();

            Reader reader = new FileReader("src/main/resources/jsons/orderHistoryList.json");
            // Transaction[] transactions = gson.fromJson(reader, Transaction[].class);

            List<OrderHistory> orderHistoryList = gson.fromJson(reader, new TypeToken<List<OrderHistory>>(){}.getType());

            for (int i = 0; i < customer.getMyCart().size(); i++) {
                OrderItem orderItem = customer.getMyCart().get(i);

                table.addCell(String.valueOf(i+1));


                table.addCell(String.valueOf(customer.getFullName()));
                table.addCell(selectedPayType.getName());
                table.addCell(orderItem.getCloth().getName());
                table.addCell(String.valueOf(orderItem.getCloth().getPrice()));
                table.addCell(String.valueOf(orderItem.getQuantity()));
                table.addCell(String.valueOf(LocalDateTime.now()));
            }

            System.out.println("Loading...");
            System.out.println("Success!!!!");

            document.add(table);
            document.close();

            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

