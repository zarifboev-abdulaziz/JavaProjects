package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.Answer;
import uz.pdp.model.Subject;
import uz.pdp.model.Test;
import uz.pdp.service.interfaceServis.TestService;

import java.util.Scanner;

// 5-Variant Online test ishlash

public class TestServiceImpl implements TestService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);



    @Override
    public void showTestMenu() {
        while (true) {
            System.out.println("1.showTestList\n2.addTest\n3.updateTest\n4.deleteTest\n5.Back");
            int testOption = scannerInt.nextInt();
            switch (testOption){
                case 1:
                    showTestList();
                    break;
                case 2:
                    addTest();
                    break;
                case 3:
                    updateTest();
                    break;
                case 4:
                    deleteTest();
                    break;
                case 5: return;
                default:
                    System.out.println("Wrong option");
                    break;
            }



        }

    }

    @Override
    public void showTestList() {
        if(Store.testList.size() == 0){
            System.out.println("List is empty");
        }

        for (Test test : Store.testList) {
            System.out.println(test.toString());
        }
    }

    @Override
    public void addTest() {

        int count = 1;
        int testId = (int)(Math.random()*10000);

        System.out.println("Enter name of the subject of the Test: ");
        String name = scannerStr.nextLine();

        System.out.println("Enter question body: ");
        String question = scannerStr.nextLine();

        while (true) {
            System.out.println("Enter option " + count + " (0=> Stop adding option): ");
            String answer = scannerStr.nextLine();
            if(answer.equals("0")) break;

            Answer answer1 = new Answer(count++, testId, answer);
            Store.answerList.add(answer1);
        }

        System.out.println("Enter true option id: ");
        int trueAnswerId = scannerInt.nextInt();

        Test test = new Test(testId, question, name, 5, trueAnswerId);
        Store.testList.add(test);
        System.out.println("Successfully added!!!");

        for (Subject subject1 : Store.subjectList) {
            if(subject1.getName().equals(name)){
                    subject1.setNumberOfTests(subject1.getNumberOfTests() + 1);
                    subject1.setScorePerTest(5);
                    subject1.setMaxBall(subject1.getMaxBall() + 5);
            }
        }



    }

    @Override
    public void updateTest() {
        System.out.println("Under Construction"); //TODO:: UpdateTest
    }

    @Override
    public void deleteTest() {
        showTestList();
        System.out.println("Enter id of a Test: ");
        int inputId = scannerInt.nextInt();

        for (Test test : Store.testList) {
            if(test.getId() == inputId){
                Store.testList.remove(test);
                System.out.println("Successfully removed");
                break;
            }
        }

    }

    @Override
    public int solveTest(Test test) { //TODO :: SolveTest
        int trueAnswers = 0;

        System.out.println("Question: " + test.getQuestion());
        System.out.println("Enter your answer: ");
        String answer = scannerStr.nextLine();

        //if(answer.equals(test.getTrueAnswer())){
         //   trueAnswers = trueAnswers + 5;
        //}
//todo;;

        return trueAnswers;



        /*
        int trueAnswers = 0;

        System.out.println(test.getQuestion());

        for (Answer answer : Store.answerList) {
            if(answer.getTestId() == test.getId()){
                System.out.println(answer);
            }
        }
        System.out.println("Enter answer id to select as a true option: ");
        int inputTrueAnswerId = scannerInt.nextInt();
        if(inputTrueAnswerId == answer1.getTrueAnswerId()){
            trueAnswers++;
        }


        System.out.println("Enter Question:  ");
        String question = scannerStr.nextLine();




        //Test test = new Test();
       // Answer answer = new Answer();

         */



    }
}
