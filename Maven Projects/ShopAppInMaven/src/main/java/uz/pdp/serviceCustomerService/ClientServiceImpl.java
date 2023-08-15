package uz.pdp.serviceCustomerService;

import com.google.gson.Gson;
import uz.pdp.DataBase;
import uz.pdp.model.Cloth;
import uz.pdp.model.PayType;
import uz.pdp.model.Transaction;
import uz.pdp.model.User;
import uz.pdp.serviceCustomerService.interfaces.ClientService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ClientServiceImpl implements ClientService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    ClientCartServiceImpl clientCartService = new ClientCartServiceImpl();

    @Override
    public void customerMenu(User user) {
        while (true) {
            System.out.println("1.Buy cloth\n2.My Cart\n3.show balance\n4.fill balance\n5.Show my purchase history\n6.Back");
            int option = scannerInt.nextInt();
            switch (option) {
                case 1:
                    buyCloth(user);
                    break;
                case 2:
                    clientCartService.myCartMenu(user);

                    break;
                case 3:
                    showBalance(user);
                    break;
                case 4:
                    fillBalance(user);
                    break;

                case 5:
                    showMyPurchaseHistory(user);
                    break;
                case 6:
                    return;
                default:
            }
        }
    }

    @Override
    public void buyCloth(User user) {
        Cloth selectedCloth = null;
        PayType selectedPayType = null;

        if (DataBase.clothList.size() == 0) {
            System.out.println("List is empty!!!");
            return;
        }

        for (Cloth cloth : DataBase.clothList) {
            System.out.println(cloth);
        }

        System.out.println("Enter cloth id to buy: ");
        int inputClothId = scannerInt.nextInt();

        for (Cloth cloth : DataBase.clothList) {
            if (cloth.getId() == inputClothId) {
                selectedCloth = cloth;
            }
        }

        for (PayType payType : DataBase.payTypeList) {
            System.out.println(payType);
        }

        System.out.println("Enter payType id to buy: ");
        int inputPayTypeId = scannerInt.nextInt();

        System.out.println("Enter quantity: ");
        int inputQuantity = scannerInt.nextInt();

        for (PayType payType : DataBase.payTypeList) {
            if (payType.getId() == inputPayTypeId) {
                selectedPayType = payType;
            }
        }

        //Transaction process
        double totalPrice = selectedCloth.getPrice() * inputQuantity;
        totalPrice = totalPrice + (totalPrice * selectedPayType.getCommissionFee() / 100);

        if (user.getBalance() < totalPrice) {
            System.out.println("you do not have enough money!!!");
            return;
        }

        user.setBalance(user.getBalance() - totalPrice);
        DataBase.admin.setBalance(DataBase.admin.getBalance() + selectedCloth.getPrice() * inputQuantity);
        if (user.getMyCart().contains(selectedCloth)) {
            user.getMyCart().remove(selectedCloth);
        }

        System.out.println("Successfully bought!!!");
        Transaction transaction = new Transaction((int) (Math.random() * 10000), user.getId(), user.getName(), selectedCloth.getName(), inputQuantity, selectedCloth.getPrice(), selectedPayType.getName());
        DataBase.transactionList.add(transaction);

        Gson gson = new Gson();
        try (
             Writer writer = new FileWriter("src/main/resources/History.json");) {

//            TypeToken<List<Transaction> transactionList>;





            String s = gson.toJson(DataBase.transactionList);

            writer.write(s);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void showMyPurchaseHistory(User user) {
        if (DataBase.transactionList.size() == 0) {
            System.out.println("List is empty!!!");
            return;
        }

        for (Transaction transaction : DataBase.transactionList) {
            if (transaction.getClientId() == user.getId()) {
                System.out.println(transaction);
            }
        }
    }

    @Override
    public void showBalance(User user) {
        System.out.println(user.getBalance());
    }

    @Override
    public void fillBalance(User user) {
        System.out.println("Enter amount: ");
        double amount = scannerInt.nextDouble();

        if (amount < 0) {
            System.out.println("Invalid amount!!!");
            return;
        }

        user.setBalance(user.getBalance() + amount);
        System.out.println("Successfully done!!!");

    }
}
