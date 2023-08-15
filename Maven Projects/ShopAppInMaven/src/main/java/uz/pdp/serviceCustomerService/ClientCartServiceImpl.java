package uz.pdp.serviceCustomerService;

import uz.pdp.DataBase;
import uz.pdp.model.Cloth;
import uz.pdp.model.PayType;
import uz.pdp.model.Transaction;
import uz.pdp.model.User;
import uz.pdp.model.enums.Size;
import uz.pdp.serviceCustomerService.interfaces.clientCartService;

import java.util.Scanner;

public class ClientCartServiceImpl implements clientCartService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public void myCartMenu(User user) {
        while (true){
            System.out.println("1.cloth List\n2.check Out\n3.Check Average Price\n4.Check Average Price By Size\n5.Back");
            int option = scannerInt.nextInt();
            switch (option){
                case 1:
                    clothList(user);
                    break;
                case 2:
                    checkOut(user);
                    break;
                case 3:
                    checkAveragePrice(user);
                    break;
                case 4:
                    checkAveragePriceBySize(user);
                    break;

                case 5: return;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }

    @Override
    public void clothList(User user) {
        if (isCartEmpty(user)){
            System.out.println("List is empty!!!");
            return;
        }

        for (Cloth cloth : user.getMyCart()) {
            System.out.println(cloth);
        }
    }

    @Override
    public void checkOut(User user) {
        Cloth selectedCloth = null;
        PayType selectedPayType = null;

        if (isCartEmpty(user)){
            System.out.println("List is empty!!!");
            return;
        }
        clothList(user);

        System.out.println("Enter cloth id to buy: ");
        int inputClothId = scannerInt.nextInt();

        ///////////////////////////////////////////////////////////////////////////////////

        for (Cloth cloth : user.getMyCart()) {
            if (cloth.getId() == inputClothId){
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
            if (payType.getId() == inputPayTypeId){
                selectedPayType = payType;
            }
        }

        //Transaction process
        if (selectedCloth.getPrice()*(100+selectedPayType.getCommissionFee())*inputQuantity > user.getBalance()){
            System.out.println("you do not have enough money!!!");
            return;
        }

        user.setBalance(user.getBalance() - selectedCloth.getPrice()*(100+selectedPayType.getCommissionFee())*inputQuantity);
        DataBase.admin.setBalance(DataBase.admin.getBalance() + selectedCloth.getPrice());
        if (user.getMyCart().contains(selectedCloth)) {
            user.getMyCart().remove(selectedCloth);
        }

        System.out.println("Successfully bought!!!");
        Transaction transaction = new Transaction((int)(Math.random()*10000), user.getId(), user.getName(), selectedCloth.getName(), inputQuantity,selectedCloth.getPrice(), selectedPayType.getName());
        DataBase.transactionList.add(transaction);

    }

    @Override
    public void checkAveragePrice(User user) {

        int count = 0;
        int sumPrice = 0;
         //Checking is empty
        if (isCartEmpty(user)){
            System.out.println("List is empty!!!");
            return;
        }

        for (Cloth cloth : user.getMyCart()) {
            count++;
            sumPrice += cloth.getPrice();
        }

        try {
            System.out.println("Average price is: " + (sumPrice/count));
        }catch (ArithmeticException e){
            System.out.println("List s empty");
        }

    }

    @Override
    public void checkAveragePriceBySize(User user) {

        Size size = null;
        int count = 0;
        int sumPrice = 0;
        //Checking is empty
        if (isCartEmpty(user)){
            System.out.println("List is empty!!!");
            return;
        }

        System.out.print("Available sizes: ");
        for (Size value : Size.values()) {
            System.out.print(value);
        }
        System.out.println("Enter size of cloth: ");
        String inputSize = scannerStr.nextLine();
        try {
            size = Size.valueOf(inputSize);
        }catch (IllegalArgumentException e){
            System.out.println("Wrong id!!!");

            System.out.print("Available sizes: ");
            for (Size value : Size.values()) {
                System.out.print(value);
            }
            return;
        }


        for (Cloth cloth : user.getMyCart()) {
            if (cloth.getSize() == size){
                count++;
                sumPrice += cloth.getPrice();
            }
        }

        try {
            System.out.println("Average price is: " + (sumPrice/count));
        }catch (ArithmeticException e){
            System.out.println("List is empty");
        }

    }

    public boolean isCartEmpty(User user){
        if (user.getMyCart().size() == 0){
            return true;
        }
        return false;
    }
}
