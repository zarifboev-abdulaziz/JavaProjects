package uz.pdp.service;

import uz.pdp.model.PayType;
import uz.pdp.service.crud.CrudRepository;
import uz.pdp.utils.DataFromJson;
import uz.pdp.utils.Db;
import uz.pdp.utils.Util;

import java.io.IOException;
import java.util.Scanner;

import static uz.pdp.utils.Util.CYAN;

public class PayTypeService implements CrudRepository {

    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    @Override
    public void menu() throws IOException {
        Util.print(CYAN,"=== PayType menu ===");
        Util.print(CYAN,"1=> Add PayType 2=> Show PayTypes 3=> Update PayType 4=> Delete PayType 5=> Back");

        int option = scannerInt.nextInt();
        switch (option) {
            case 1:
                create();
                break;
            case 2:
                read();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;

            case 5:return;
            default:
                Util.print(Util.RED,"Wrong option!");
        }
        menu();

    }

    @Override
    public void create() throws IOException {
        System.out.println("Enter pay type name: ");
        String name = scannerStr.nextLine();

        System.out.println("Enter pay type discount: ");
        double commissionFee = scannerInt.nextDouble();

        PayType payType = new PayType(name,commissionFee);

        Db.payTypeList.add(payType);

        DataFromJson.updateJson();
        System.out.println("Successfully Done!!!");
    }

    @Override
    public void read() {
        System.out.println("---Payment type list---");
        for (PayType payType : Db.payTypeList) {
            System.out.println(payType);
        }
    }

    @Override
    public void update() throws IOException {
        read();

        System.out.println("Enter Pay Type Name to update: ");
        String inputName = scannerStr.nextLine();

        for (PayType payType : Db.payTypeList) {
            if(payType.getName().equalsIgnoreCase(inputName)){
                System.out.println("Enter new pay type name: ");
                String name = scannerStr.nextLine();

                System.out.println("Enter new pay type commission fee: ");
                double commissionFee = scannerInt.nextDouble();

                payType.setName(name);
                payType.setCommissionFee(commissionFee);
            }
        }
        System.out.println("Successfully updated!");

        DataFromJson.updateJson();
    }

    @Override
    public void delete() {
        read();

        System.out.println("Enter pay type Name to delete: ");
        int id = scannerInt.nextInt();
        String inputName = scannerStr.nextLine();

        Db.payTypeList.removeIf(payType -> payType.getName().equalsIgnoreCase(inputName));
        System.out.println("Successfully deleted!");

        DataFromJson.updateJson();
    }
}
