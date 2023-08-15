package uz.pdp.service;

import uz.pdp.DataBase;
import uz.pdp.model.PayType;
import uz.pdp.model.User;
import uz.pdp.service.interfaces.PayTypeService;

import java.util.Scanner;

public class PayTypeServiceImpl implements PayTypeService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    @Override
    public void payTypeMenu(User user) {
        while (true){
            System.out.println("1.show Pay Type List\n2.add PayType\n3.update PayType\n4.delete PayType\n5.Back");
            int option = scannerInt.nextInt();
            switch (option){
                case 1:
                    showPayTypeList();
                    break;
                case 2:
                    addPayType();
                    break;
                case 3:
                    updatePayType();
                    break;
                case 4:
                    deletePayType();
                    break;

                case 5: return;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }

    }

    @Override
    public void showPayTypeList() {
        if (DataBase.payTypeList.size() == 0){
            System.out.println("List is empty!!!");
            return;
        }

        for (PayType payType : DataBase.payTypeList) {
            System.out.println(payType);
        }

    }

    @Override
    public void addPayType() {
        int id = (int)(Math.random()*10000);

        System.out.println("Enter name of the pay type: ");
        String name = scannerStr.nextLine();

        System.out.println("Enter commission fee of the pay type: ");
        double commissionFee = scannerInt.nextDouble();

        PayType payType = new PayType(id, name, commissionFee);
        DataBase.payTypeList.add(payType);
        System.out.println("Successfully added");

    }

    @Override
    public void updatePayType() {
        if (DataBase.payTypeList.size() == 0){
            System.out.println("List is empty!!!");
            return;
        }
        showPayTypeList();

        System.out.println("Enter payType id to Update: ");
        int inputId = scannerInt.nextInt();

        for (PayType payType : DataBase.payTypeList) {
            if (payType.getId() == inputId){
                System.out.println("Enter new Commission Fee (in percentage): ");
                double newCommissionFee = scannerInt.nextInt();

                payType.setCommissionFee(newCommissionFee);
                System.out.println("Successful update!!!");
                break;
            }
        }

    }

    @Override
    public void deletePayType() {
        if (DataBase.payTypeList.size() == 0){
            System.out.println("List is empty!!!");
            return;
        }
        showPayTypeList();

        System.out.println("Enter payType id to delete: ");
        int inputId = scannerInt.nextInt();

        for (PayType payType : DataBase.payTypeList) {
            if (payType.getId() == inputId){
                DataBase.payTypeList.remove(payType);

                System.out.println("Successful delete!!!");

                break;
            }
        }

    }
}
