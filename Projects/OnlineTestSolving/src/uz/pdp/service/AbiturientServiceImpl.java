package uz.pdp.service;

import uz.pdp.DB;
import uz.pdp.model.PayType;
import uz.pdp.model.User;
import uz.pdp.service.interfaces.AbiturientService;

public class AbiturientServiceImpl implements AbiturientService {


    @Override
    public void abiturientMenu(User abiturient) {
        while (true){
            System.out.println("1.choose Subject\n2.show solved tests History\n3.fill Balance\n4.Log out");
            int option = DB.scannerInt.nextInt();
            switch (option){
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: return;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }


    }

    @Override
    public void chooseSubject() {

    }

    @Override
    public void showUserTestSolveHistory() {

    }

    @Override
    public void fillBalance(User abiturient) {
        System.out.println("Enter amount: ");
        double amount = DB.scannerInt.nextDouble();

        if(amount < 0){
            System.out.println("Invalid Amount: ");
            return;
        }

        for (PayType payType : DB.payTypeList) {
            System.out.println(payType);
        }

        System.out.println("Enter id of pay type: ");

        abiturient.setBalance(abiturient.getBalance() + amount);


    }
}
