package uz.pdp.service;

import uz.pdp.DB;
import uz.pdp.model.FillBalance;
import uz.pdp.model.User;
import uz.pdp.service.interfaces.AdminService;

public class AdminServiceImpl implements AdminService {
    SubjectServiceImpl subjectService = new SubjectServiceImpl();
    PayTypeServiceImpl payTypeService = new PayTypeServiceImpl();
    TestServiceImpl testService = new TestServiceImpl();
    AnswerServiceImpl answerService = new AnswerServiceImpl();


    @Override
    public void adminMenu(User admin) {
        while (true){
            System.out.print("1.Subject Menu\n2.Test Menu\n3.Pay Type Menu\n4.Fill Balance History\n5.Answer Menu\n6.Log out\n=>");
            int adminOption = DB.scannerInt.nextInt();
            switch (adminOption){
                case 1:
                    subjectService.subjectMenu();
                    break;
                case 2:
                    testService.showTestMenu();
                    break;
                case 3:
                    payTypeService.showPayTypeMenu();
                    break;
                case 4:
                    showFillBalanceHistory();
                    break;
                case 5:
                    answerService.showAnswerMenu();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Wrong option\n");
                    break;
            }

        }
    }

    @Override
    public void showFillBalanceHistory() {
        for (FillBalance fillBalance : DB.fillBalanceList) {
            System.out.println(fillBalance);
        }
    }
}
