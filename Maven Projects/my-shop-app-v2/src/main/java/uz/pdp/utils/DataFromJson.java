package uz.pdp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bouncycastle.util.Store;
import uz.pdp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static uz.pdp.utils.Db.*;
import static uz.pdp.utils.Util.*;
import static uz.pdp.utils.Util.CYAN;
import static uz.pdp.utils.Util.print;

public abstract class DataFromJson {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void getAllDataFromJson() {
        usersFromJson();
        colorsFromJson();
        clothsFromJson();
        storeFromJson();
        payTypesFromJson();
        orderHistoryFromJson();

        print(GREEN, "All data are ready...");
    }

    public static void updateJson(){
        //Update Users
        try (Writer writer = new FileWriter("src/main/resources/jsons/customers.json")) {

            String jsonStr = gson.toJson(userList, new TypeToken<ArrayList<Customer>>() {}.getType());

            //print(CYAN, jsonStr);
            writer.write(jsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Update Colors
        try (Writer writer = new FileWriter("src/main/resources/jsons/colors.json")) {

            String jsonStr = gson.toJson(colorList,
                    new TypeToken<ArrayList<Color>>() {
                    }.getType());

            //print(CYAN, jsonStr);
            writer.write(jsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Update Clothes
        try (Writer writer = new FileWriter("src/main/resources/jsons/cloths.json")) {


            String jsonStr = gson.toJson(clothList,
                    new TypeToken<ArrayList<Cloth>>() {
                    }.getType());

            //print(CYAN, jsonStr);
            writer.write(jsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Update Store
        try (Writer writer = new FileWriter("src/main/resources/jsons/store.json")) {
            String jsonStr = gson.toJson(store,
                    new TypeToken<ArrayList<StoreItem>>() {
                    }.getType());

            //print(CYAN, jsonStr);
            writer.write(jsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Update PayType
        try (Writer writer = new FileWriter("src/main/resources/jsons/payTypeList.json")) {
            String jsonStr = gson.toJson(payTypeList,
                    new TypeToken<ArrayList<PayType>>() {
                    }.getType());

            writer.write(jsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Update Order History
        try (Writer writer = new FileWriter("src/main/resources/jsons/orderHistoryList.json")) {
            String jsonStr = gson.toJson(orderHistoryList,
                    new TypeToken<ArrayList<OrderHistory>>() {
                    }.getType());

            writer.write(jsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void usersFromJson() {
        // GET THE ADMIN FROM JSON AND ADD TO THE USER LIST
        try (Reader readerForAdmin = new FileReader("src/main/resources/jsons/admin.json")) {
            Admin admin = gson.fromJson(readerForAdmin, Admin.class);
            userList.add(admin);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // GET CUSTOMERS FROM JSON AND ADD THEM TO THE USER LIST
        try (Reader readerForCustomers =
                     new FileReader("src/main/resources/jsons/customers.json")) {

            List<Customer> customerListFromJson = gson.fromJson(readerForCustomers,
                    new TypeToken<ArrayList<Customer>>() {
                    }.getType());

            userList.addAll(customerListFromJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void colorsFromJson() {
        // GET COLORS FROM JSON AND ADD THEM TO THE COLOR LIST
        try (Reader readerForColors =
                     new FileReader("src/main/resources/jsons/colors.json")) {

            List<Color> colorListFromJson = gson.fromJson(readerForColors,
                    new TypeToken<ArrayList<Color>>() {
                    }.getType());

            colorList.addAll(colorListFromJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clothsFromJson() {
        // GET COLORS FROM JSON AND ADD THEM TO THE COLOR LIST
        try (Reader readerForCloths =
                     new FileReader("src/main/resources/jsons/cloths.json")) {

            List<Cloth> clothListFromJson = gson.fromJson(readerForCloths,
                    new TypeToken<ArrayList<Cloth>>() {
                    }.getType());

            clothList.addAll(clothListFromJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void storeFromJson() {
        // GET COLORS FROM JSON AND ADD THEM TO THE COLOR LIST
        try (Reader readerForStores =
                     new FileReader("src/main/resources/jsons/store.json")) {

            List<StoreItem> storeItemsFromJson = gson.fromJson(readerForStores,
                    new TypeToken<ArrayList<StoreItem>>() {
                    }.getType());

            store.addAll(storeItemsFromJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void payTypesFromJson() {
        // GET COLORS FROM JSON AND ADD THEM TO THE COLOR LIST
        try (Reader readerForPayType =
                     new FileReader("src/main/resources/jsons/payTypeList.json")) {

            List<PayType> payTypeListFromJson = gson.fromJson(readerForPayType,
                    new TypeToken<ArrayList<PayType>>() {
                    }.getType());

            payTypeList.addAll(payTypeListFromJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void orderHistoryFromJson() {
        // GET COLORS FROM JSON AND ADD THEM TO THE COLOR LIST
        try (Reader readerForOrderHistory =
                     new FileReader("src/main/resources/jsons/orderHistoryList.json")) {

            List<OrderHistory> orderHistoryListFromJson = gson.fromJson(readerForOrderHistory,
                    new TypeToken<ArrayList<OrderHistory>>() {
                    }.getType());

            orderHistoryList.addAll(orderHistoryListFromJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
