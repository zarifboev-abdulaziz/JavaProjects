package uz.pdp.service.adminService.crud;

import uz.pdp.DataBase;
import uz.pdp.model.PayType;
import uz.pdp.service.adminService.crud.interfaces.PayTypeService;

import java.util.Scanner;

public class PayTypeServiceImpl implements PayTypeService {
    @Override
    public void payTypeMenu() {
        System.out.println("[1] Show Pay Types | [2] Add PayType | [3] Update PayType | [4] Delete PayType | [5] Back \n-> ");
        int option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1:
                showPayTypeList();
                break;
            case 2:
                addPayType();
                break;
            case 3:
                upDatePayType();
                break;
            case 4:
                deletePayType();
                break;
            case 5:
                return;
            default:
                System.out.println("Wrong option!!! Please, try again!");
                break;
        }
        payTypeMenu();
    }

    @Override
    public void showPayTypeList() {
        DataBase.payTypeList.forEach(System.out::println);
    }

    @Override
    public void addPayType() {
        showPayTypeList();
        System.out.print("\nEnter PayType name: ");
        String name = new Scanner(System.in).nextLine();

        for (PayType payType : DataBase.payTypeList) {
            if (payType.getName().equalsIgnoreCase(name)) {
                System.out.println(payType.getName() + " already exists!!!");
                return;
            }
        }

        System.out.print("Enter id (unique): ");
        int id = new Scanner(System.in).nextInt();
        System.out.print("Enter commissionFee: ");
        double commissionFee = new Scanner(System.in).nextDouble();

        PayType payType = new PayType(id,name,commissionFee);
        DataBase.payTypeList.add(payType);
        System.out.println(payType.getName() + " successfully added!!!");
    }

    @Override
    public void upDatePayType() {
        showPayTypeList();
        System.out.print("\nEnter PayType name: ");
        String name = new Scanner(System.in).nextLine();

        for (PayType payType : DataBase.payTypeList) {
            if (payType.getName().equalsIgnoreCase(name)) {
                System.out.print("\nEnter new name of PayType: ");
                String newName = new Scanner(System.in).nextLine();
                System.out.print("Enter new id (unique) of PayType: ");
                int id = new Scanner(System.in).nextInt();
                System.out.print("Enter new commissionFee of PayType: ");
                double commissionFee = new Scanner(System.in).nextDouble();
                PayType newPayType = new PayType(id,newName,commissionFee);
                DataBase.payTypeList.add(newPayType);
                System.out.println(payType.getName() + " successfully updated!!!");
                DataBase.payTypeList.remove(payType);
                return;
            }
        }
        System.out.println("Not found PayType!!! Please, try again!");
    }

    @Override
    public void deletePayType() {
        showPayTypeList();
        System.out.print("\nEnter PayType name: ");
        String name = new Scanner(System.in).nextLine();

        for (PayType payType : DataBase.payTypeList) {
            payType.getName().equalsIgnoreCase(name);
            System.out.println(payType.getName() + " successfully deleted!!!");
            DataBase.payTypeList.remove(payType);
            return;
        }
        System.out.print("Not found PayType!!! Please, try again!\n");
    }
}
