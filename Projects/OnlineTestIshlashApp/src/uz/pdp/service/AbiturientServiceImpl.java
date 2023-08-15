package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.*;
import uz.pdp.service.interfaceServis.AbiturientService;
import uz.pdp.service.interfaceServis.TestService;

import javax.jws.soap.SOAPBinding;
import java.util.Scanner;

public class AbiturientServiceImpl implements AbiturientService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    TestServiceImpl testService = new TestServiceImpl();
    SubjectServisImpl subjectServis = new SubjectServisImpl();
    PayTypeServiceImpl payTypeService  =new PayTypeServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();



    @Override
    public void showAbiturientMenu(User abiturient) {

        while (true) {
            System.out.println("1.Subject Tanlash\n2.Show solved tests history\n3.fillBalance\n4.Check Balance\n5.Log Out\nChoose:");
            int abiturientOption = scannerInt.nextInt();
            switch (abiturientOption) {
                case 1:
                    chooseSubject(abiturient);
                    break;
                case 2:
                    testHistory();
                    break;
                case 3:
                    fillBalance(abiturient);
                    break;
                case 4:
                    userService.checkBalance(abiturient);
                    break;
                case 5:
                    return;

                default:
                    System.out.println("Wrong option");
            }
        }
    }

    @Override
    public void chooseSubject(User abiturient) {
        if(Store.subjectList.size() == 0){
            System.out.println("List is empty");
            return;
        }

        for (Subject subject : Store.subjectList) {
            System.out.println("Subject id: " + subject.getId() +", Subject Name: " + subject.getName() + ", Number of Tests: " + subject.getNumberOfTests() + ", Max Ball: " + subject.getMaxBall() + ", Score Per Test: " + subject.getScorePerTest() + ", Price: " + subject.getPrice());
        }

        System.out.println("Enter subject id to start the test: ");
        int inputId = scannerInt.nextInt();

        Subject selectedSubject = null;
        Test selectedTest = null;
        Answer selectedAnswers = null;
        int sumTrueAnswers = 0;
        int countTrueAnswers = 0;

        for (Subject subject : Store.subjectList) {
            if(inputId == subject.getId()){
                selectedSubject = subject;

                if(subject.getPrice() > abiturient.getBalance()){
                    System.out.println("Hisobda yetarli pul mavjud emas");
                    return;
                } else {
                    abiturient.setBalance(abiturient.getBalance() - subject.getPrice());
                }
                System.out.println("Test Boshlandi: ");
            }
        }

        //searching tests
        for (Test test : Store.testList) {
            if(test.getBelongedSubject().equals(selectedSubject.getName())){
                selectedTest = test;
                //Searching test Answers
                System.out.println(test);

                for (Answer answer : Store.answerList) {
                    if(answer.getTestId() == selectedTest.getId()){
                        System.out.println(answer);
                    }
                }

                System.out.println("Choose true answer and enter id: ");
                int userAnswerId = scannerInt.nextInt();
                if(userAnswerId == selectedTest.getTrueAnswerID()){
                    countTrueAnswers++;
                }
            }
        }

        System.out.println("Test tugadi!!!");

        UserTestSolveHistory userTestSolveHistory = new UserTestSolveHistory((int)(Math.random()*10000),selectedSubject.getName(), selectedSubject.getMaxBall(), countTrueAnswers*selectedSubject.getScorePerTest());
        Store.userTestSolveHistoryList.add(userTestSolveHistory);
        
    }

    @Override
    public void testHistory() {
        subjectServis.showSubjectList();
        System.out.println("Enter ID to see subject history: ");
        int inputId = scannerInt.nextInt();

        for (Subject subject : Store.subjectList) {
            if(subject.getId() == inputId){
                for (UserTestSolveHistory userTestSolveHistory : Store.userTestSolveHistoryList) {
                  if(userTestSolveHistory.getSubjectName().equals(subject.getName())){
                      System.out.println(userTestSolveHistory);
                  }
                }
            }
            break;
        }
    }

    @Override
    public void fillBalance(User abiturient) {
        payTypeService.showPayTypeList();
        System.out.println("Enter id to select: ");
        int inputId = scannerInt.nextInt();

        PayType selectedPayType = null;
        double amount = 0;

        for (PayType payType : Store.payTypeList) {
            if(inputId == payType.getId()){
                selectedPayType = payType;
                System.out.println("Enter amount: ");
                 amount = scannerInt.nextDouble();

                abiturient.setBalance(abiturient.getBalance() + amount);
//                sessionUser.setBalance(sessionUser.getBalance()+ amount);
                System.out.println("Succesfully done!!!");
            }
        }

        FillBalanceHistory fillBalanceHistory = new FillBalanceHistory((int)(Math.random()*10000), abiturient.getUserName(), selectedPayType.getName(), amount);

    }
}
