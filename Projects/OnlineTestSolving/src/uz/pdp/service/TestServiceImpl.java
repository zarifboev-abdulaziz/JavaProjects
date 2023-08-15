package uz.pdp.service;

import uz.pdp.DB;
import uz.pdp.model.Answer;
import uz.pdp.model.Subject;
import uz.pdp.model.Test;
import uz.pdp.service.interfaces.TestService;

public class TestServiceImpl implements TestService {
    @Override
    public void showTestMenu() {
        while (true){
            System.out.print("1.show Test List\n2.add Test\n3.update Test\n4.delete Test\n5.Back\n=>");
            int testOption = DB.scannerInt.nextInt();
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
                    System.out.println("Wrong option!!!");
                    break;
            }

        }

    }

    @Override
    public void showTestList() {
        if(DB.testList.size() == 0){System.out.println("List is empty"); return;}
        for (Test test : DB.testList) {
            System.out.println(test);
        }

    }

    @Override
    public void addTest() {
        int id = (int)(Math.random()*10000);

        System.out.println("Enter subject of the test: ");
        String belongedSubject = DB.scannerStr.nextLine();

        System.out.println("Enter question Body: ");
        String questionBody = DB.scannerStr.nextLine();
        int count = 0;

        while (true){
            System.out.println("Enter option " + (++count) + " body (0=> Back): ");
            String body = DB.scannerStr.nextLine();

            if(body.equals("0")){
                break;
            }

            Answer answer = new Answer(count, id, body);
            DB.answerList.add(answer);
        }



        System.out.println("Enter true answer id: ");
        int trueAnswerId = DB.scannerInt.nextInt();

        Test test = new Test(id, questionBody, belongedSubject, trueAnswerId);
        DB.testList.add(test);
        System.out.println("Test successfully added.");

        for (Subject subject : DB.subjectList) {
            if(subject.getName().equals(belongedSubject)){
                subject.setNumberOfTests(subject.getNumberOfTests() + 1);
                subject.setMaxBall(subject.getNumberOfTests() * 5);
            }
        }

    }

    public boolean checkId(int id){
        for (Test test : DB.testList) {
            if(test.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateTest() {
        if(DB.testList.size() == 0){System.out.println("List is empty"); return;}

        System.out.println("Enter subject of the test :");
        String subjectName = DB.scannerStr.nextLine();

        for (Test test : DB.testList) {
            if(test.getBelongedSubject().equals(subjectName)){
                System.out.println(test);
            }
        }

        System.out.println("Enter id of the Test: ");
         int inputId = DB.scannerInt.nextInt();

        if (!checkId(inputId)) {
            System.out.println("Test not found!!!");
            return;
        }

        for (Test test : DB.testList) {
            if(test.getId() == inputId){
                System.out.println("Enter new Subject for the test: ");
                String name = DB.scannerStr.nextLine();

                System.out.println("Enter new body for the test: ");
                String questionBody = DB.scannerStr.nextLine();

                for (Answer answer : DB.answerList) {
                    if(answer.getTestId() == test.getId()){
                        System.out.println(answer);
                    }
                }

                System.out.println("\nEnter true answer id of the test:");
                int trueAnswerID = DB.scannerInt.nextInt();

                test.setBelongedSubject(name);
                test.setQuestionBody(questionBody);
                test.setTrueAnswerId(trueAnswerID);
            }
        }

    }

    @Override
    public void deleteTest() {
        if(DB.testList.size() == 0){System.out.println("List is empty"); return;}

        System.out.println("Enter subject of the test :");
        String subjectName = DB.scannerStr.nextLine();

        for (Test test : DB.testList) {
            if(test.getBelongedSubject().equals(subjectName)){
                System.out.println(test);
            }
        }

        System.out.println("Enter id of the Test: ");
        int inputId = DB.scannerInt.nextInt();

        if (!checkId(inputId)) {
            System.out.println("Test not found!!!");
            return;
        }

        for (Test test : DB.testList) {
            if(test.getId() == inputId){
                DB.testList.remove(test);
                System.out.println("Successfully Deleted");
            }
        }


    }
}
