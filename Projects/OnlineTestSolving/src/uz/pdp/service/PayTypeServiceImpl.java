package uz.pdp.service;

import uz.pdp.DB;
import uz.pdp.model.PayType;
import uz.pdp.service.interfaces.PayTypeService;

public class PayTypeServiceImpl implements PayTypeService {
    @Override
    public void showPayTypeMenu() {
        while (true){
            System.out.print("1.show Pay Type List\n2.add Pay Type\n3.update Pay Type\n4.delete Pay Type\n5.Back\n=>");
            int payTypeOption = DB.scannerInt.nextInt();
            switch (payTypeOption){
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
                    System.out.println("wrong option!!!");
            }
        }
    }

    @Override
    public void showPayTypeList() {
        if(DB.payTypeList.size() == 0){System.out.println("List is empty."); return;}

        DB.payTypeList.forEach(System.out::println);
    }


    @Override
    public void addPayType() {
        int id = (int)(Math.random()*10000);

        System.out.println("Enter name of the pay type: ");
        String name = DB.scannerStr.nextLine();

        for (PayType payType : DB.payTypeList) {
            if(payType.getName().equals(name)){
                System.out.println("Subject exists.");
                return;
            }
        }

        System.out.println("Enter commission Fee of the pay type: ");
        double commissionFee = DB.scannerInt.nextDouble();

        PayType payType = new PayType(id, name, commissionFee);
        DB.payTypeList.add(payType);
        System.out.println("Successfully added!!!");

    }

    public boolean checkId(int id){
        for (PayType payType : DB.payTypeList) {
            if(payType.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updatePayType() {
        if(DB.payTypeList.size() == 0){System.out.println("List is empty."); return;}
        showPayTypeList();
        System.out.println("Enter id of the pay type: ");
        int inputId = DB.scannerInt.nextInt();

        if (!checkId(inputId)) {
            System.out.println("Pay type not found!!!\n");
            return;
        }

        System.out.println("Enter new name of payType: ");
        String newName = DB.scannerStr.nextLine();

        System.out.println("Enter new commission Fee: ");
        double commissionFee = DB.scannerInt.nextDouble();

        for (PayType payType : DB.payTypeList) {
            if(payType.getId() == inputId){
                payType.setName(newName);
                payType.setCommissionFee(commissionFee);
                System.out.println("Successfully updated!!!\n");
                break;
            }
        }

    }

    @Override
    public void deletePayType() {
        if(DB.payTypeList.size() == 0){System.out.println("List is empty."); return;}
        showPayTypeList();
        System.out.println("Enter id of the pay type: ");
        int inputId = DB.scannerInt.nextInt();

        if (!checkId(inputId)) {
            System.out.println("Pay type not found!!!\n");
            return;
        }

        for (PayType payType : DB.payTypeList) {
            if(payType.getId() == inputId){
                DB.payTypeList.remove(payType);
                System.out.println("Successfully deleted!!!");
                break;
            }
        }
    }
}
