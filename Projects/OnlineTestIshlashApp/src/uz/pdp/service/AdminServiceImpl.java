package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.FillBalanceHistory;
import uz.pdp.model.Subject;
import uz.pdp.model.User;
import uz.pdp.service.interfaceServis.AdminService;

import java.util.Scanner;

public class AdminServiceImpl implements AdminService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    SubjectServisImpl subjectServis = new SubjectServisImpl();
    TestServiceImpl testService = new TestServiceImpl();
    PayTypeServiceImpl payTypeService = new PayTypeServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();



    @Override
    public void showAdminMenu(User admin) {

        while (true) {
            System.out.println("1.Subject Menu\n2.Test Menu\n3.PayType Menu\n4.Fill balance History\n5.Check Balance\n6.Log Out\nChoose one: ");
            int adminOption = scannerInt.nextInt();
            switch (adminOption) {
                case 1:
                    subjectServis.showSubjectMenu();
                    break;
                case 2:
                    testService.showTestMenu();
                    break;
                case 3:
                    payTypeService.showPayTypeMenu();
                    break;
                case 4:fillBalanceHistory();
                    break;
                case 5:
                    userService.checkBalance(admin);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }
    }

    @Override
    public void fillBalanceHistory() {
        for (FillBalanceHistory fillBalanceHistory : Store.fillBalanceHistoryList) {
            System.out.println(fillBalanceHistory.toString());
        }

    }


}
