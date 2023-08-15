package uz.pdp.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.security.krb5.internal.crypto.Des;
import uz.pdp.model.*;
import uz.pdp.model.abs.User;
import uz.pdp.model.enums.Role;
import uz.pdp.service.clientService.ClientMenuServiceImpl;
import uz.pdp.service.clientService.FilterClothesServiceImpl;
import uz.pdp.service.interfaces.MainService;
import uz.pdp.utils.DataFromJson;
import uz.pdp.utils.Db;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static uz.pdp.utils.Util.*;
import static uz.pdp.utils.Db.*;

public class MainServiceImpl implements MainService {

    AuthServiceImpl authService = new AuthServiceImpl();
    AdminServiceImpl adminService = new AdminServiceImpl();
    ClientMenuServiceImpl clientMenuService = new ClientMenuServiceImpl();
    FilterClothesServiceImpl filterClothesService = new FilterClothesServiceImpl();

    User guestUser = new Customer();
    Customer guestCustomer = (Customer) this.guestUser;

    @Override
    public void showMenu() {
        print(BLUE, "1=> See clothes, 2=> Filter Clothes 3=> My cart, 4=> Login, 5=> Register, 0=> Exit");
        System.out.print(BLUE + "option: " + RESET);
        int option = scnInt();
        switch (option) {
            case 1:
                printInRed("======= CLOTHES ======");
                seeClothes();
                break;
            case 2:
                filterClothesService.filterClothesMenu();
                break;
            case 3:
                printInRed("======= MY CART ======");
                showCustomerCart();
                myCartMenu();
                break;

            case 4:
                printInRed("======= LOGIN ======");
                User authorizedUser = authService.login();
                if (authorizedUser == null){
                    System.err.println("User not Found!!!");
                    break;
                }

                print(CYAN, "Welcome " + authorizedUser.getFullName());
                if (authorizedUser.getRole().equals(Role.ADMIN)) {
                    adminService.showAdminMenu();
                    break;
                } else {
                    guestUser = authorizedUser;
                    Customer newCustomer = (Customer) guestUser;
                    newCustomer.getMyCart().addAll(guestCustomer.getMyCart());
                    clientMenuService.clientMenu(newCustomer);

                    guestUser = null;
                    guestUser = new Customer();
                    guestCustomer = (Customer) guestUser;
                }
                break;
            case 5:
                printInRed("======= REGISTER ======");
                authService.register();
                break;
            case 0: return;
            default:
                printInRed("Wrong option");
                break;
        }
        showMenu();
    }

    private void myCartMenu() {
        print(GREEN_BOLD, "1=> Add more, 2=> Checkout, 0=> Back");
        System.out.print("=> ");
        int cartOption = scnInt();
        switch (cartOption) {
            case 1:
                seeClothes();
                break;
            case 2:
                if (guestUser.getUsername() == null) {
                    System.out.println("PLease, log in or register to check out!!!");
                }

                System.out.println("1=> Login 2=> Register 3=> back");
                int option = scannerInt.nextInt();
                switch (option){
                    case 1:
                        printInRed("======= LOGIN ======");
                        User authorizedUser = authService.login();
                        if (authorizedUser == null){
                            System.err.println("User not Found!!!");
                            break;
                        }

                        print(CYAN, "Welcome " + authorizedUser.getFullName());
                        if (authorizedUser.getRole().equals(Role.ADMIN)) {
                            adminService.showAdminMenu();
                            break;
                        } else {
                            guestUser = authorizedUser;
                            Customer newCustomer = (Customer) guestUser;
                            newCustomer.getMyCart().addAll(guestCustomer.getMyCart());
                            clientMenuService.clientMenu(newCustomer);

                            guestUser = null;
                            guestUser = new Customer();
                            guestCustomer = (Customer) guestUser;
                        }
                        break;
                    case 2:
                        authService.register();
                        break;
                    default:
                        System.err.println("Wrong option!!!");
                        break;
                }

                break;
            case 0:
                break;
            default:
                print(RED, "Wrong option!!");
                break;
        }
    }

    private void showCustomerCart() {
        for (int i = 0; i < guestCustomer.getMyCart().size(); i++) {
            OrderItem orderItem = guestCustomer.getMyCart().get(i);
            String discount = orderItem.getCloth().getDiscount() > 0 ?
                    BLACK_CROSSED_OUT + orderItem.getCloth().getPrice() + RESET +
                            " " + printInRed(orderItem.getCloth().getPrice() - orderItem.getCloth().getPrice() * orderItem.getCloth().getDiscount() / 100 +
                            " so'm")

                    : String.valueOf(orderItem.getCloth().getPrice());


            print(CYAN, i + 1 + ". " + " Cloth: " + orderItem.getCloth().getName()
                    + ", Product code: " + orderItem.getCloth().getProductCode()
                    + ", Quantity: " + orderItem.getQuantity()
                    +", Price: "  + discount
            );
        }
    }

    @Override
    public void seeClothes() {
        for (int i = 0; i < clothList.size(); i++) {
            Cloth cloth = clothList.get(i);
            String discount = cloth.getDiscount() > 0 ?
                    BLACK_CROSSED_OUT + cloth.getPrice() + RESET +
                            " " + printInRed(cloth.getPrice() - cloth.getPrice() * cloth.getDiscount() / 100 +
                            " so'm")

                    : String.valueOf(cloth.getPrice());
            print(CYAN, (i + 1) + ". "
                    + cloth.getProductCode() + ", "
                    + cloth.getName() + ", "
                    + cloth.getColor().getName() + ", "
                    + cloth.getSize() + ", "
                    + discount
            );
        }
        print(GREEN_BOLD, "1=> Enter product code 2=> Prev 3=> Next 0=> Back");
        System.out.print("=> ");
        int option = scnInt();
        switch (option) {
            case 1:
                Cloth selectedCloth = selectClothByProductCode();
                if (selectedCloth == null) return;

                print(GREEN_BOLD, "Enter quantity: ");
                System.out.print("=> ");
                int quantity = scnInt();

                boolean isAdded = addToCart(selectedCloth, quantity);

                break;
            case 2:
                System.out.println("Under Construction !!!");
                break;
            case 3:
                System.out.println("Under Construction !!!");
                break;
            case 0:
                return;
            default:
                print(RED, "Wrong option!!");
                break;
        }
    }

    private boolean addToCart(Cloth selectedCloth, int quantity) {
        for (StoreItem storeItem : store) {
            if (storeItem.getCloth().getId().equals(selectedCloth.getId())) {
                if (storeItem.getQuantity() < quantity) {
                    if (storeItem.getQuantity() == 0) {
                        System.out.println(printInRed(selectedCloth.getName() + " is out of " +
                                "stock!!!"));
                    } else {
                        System.out.println(printInRed("We have left only " + storeItem.getQuantity() + "!"));
                    }
                    return false;
                } else {

                    // TODO: 09.12.2021 ADD CLOTH AND QUANTITY (OrderItem) to customer's cart!! - DONE
                    boolean isFound = false;
                    for (OrderItem orderItem : guestCustomer.getMyCart()) {
                        if (orderItem.getCloth().getId().equals(selectedCloth.getId())){
                            isFound = true;
                            orderItem.setQuantity(orderItem.getQuantity() + quantity);
                        }
                    }

                    if (isFound){
                        print(BLUE_BOLD, selectedCloth.getName() + " has been added to the cart " +
                                "successfully!!");
                    }else {
                        guestCustomer.getMyCart().add(new OrderItem(selectedCloth, quantity));
                        print(BLUE_BOLD, selectedCloth.getName() + " has been added to the cart " +
                                "successfully!!");
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private Cloth selectClothByProductCode() {
        System.out.print(GREEN + "Code: " + RESET);
        int inputProductCode = scnInt();
        if (inputProductCode == 0) {
            return null;
        }
        for (Cloth cloth : clothList) {
            if (cloth.getProductCode() == inputProductCode) {
                return cloth;
            }
        }
        System.out.println(printInRed("Wrong product code!!! Enter product code again (0=> Back):" +
                " "));
        return selectClothByProductCode();
    }

}
