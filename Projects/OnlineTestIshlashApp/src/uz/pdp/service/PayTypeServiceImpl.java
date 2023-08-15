package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.PayType;
import uz.pdp.service.interfaceServis.PayTypeService;

import java.util.Scanner;
import java.util.SortedMap;

public class PayTypeServiceImpl implements PayTypeService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    @Override
    public void showPayTypeMenu() {
        while (true){
            System.out.println("1.showPayTypeList\n2.addPayType\n3.updatePayType\n4.deletePayType\n5.Back");
            int payTypeOption = scannerInt.nextInt();
            switch (payTypeOption){
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: return;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }

    @Override
    public void showPayTypeList() {
        if(Store.payTypeList.size() == 0){
            System.out.println("List is empty");
            return;
        }

        for (PayType payType : Store.payTypeList) {
            System.out.println(payType.toString());
        }
    }

    @Override
    public void addPayType() {

        System.out.println("Enter name of payment type: ");
        String name = scannerStr.nextLine();

        System.out.println("Enter comission fee in percentage : ");
        double commissionFee = scannerInt.nextDouble();

        PayType payType = new PayType((int)(Math.random()*10000), name, commissionFee);

    }

    @Override
    public void updatePayType() {
        System.out.println("Under Construction");

    }

    @Override
    public void deletePayType() {
        showPayTypeList();
        System.out.println("Enter payment type ID; ");
        int inputId = scannerInt.nextInt();

        for (PayType payType : Store.payTypeList) {
            if(inputId == payType.getId()){
                Store.payTypeList.remove(payType);
                System.out.println("Succesfully deleted");
                break;
            }
        }

    }
}
