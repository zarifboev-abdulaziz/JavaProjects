package uz.pdp.service;

import com.sun.scenario.effect.impl.state.AccessHelper;
import uz.pdp.DB;
import uz.pdp.model.Answer;
import uz.pdp.model.Subject;
import uz.pdp.model.Test;
import uz.pdp.service.interfaces.AnswerService;

public class AnswerServiceImpl implements AnswerService {


    @Override
    public void showAnswerMenu() {
        while (true){
            System.out.print("1.show Answer List\n2.add Answer\n3.update Answer\n4.delete Answer\n5.Back\n=>");
            int answerOption = DB.scannerInt.nextInt();
            switch (answerOption){
                case 1:break;
                case 2:break;
                case 3:break;
                case 4:break;
                case 5:
                    return;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }

    @Override
    public void showAnswerList() {
        if(DB.answerList.size() == 0){System.out.println("List is empty!!!"); return;}

        for (Answer answer : DB.answerList) {
            System.out.println(answer);
        }
    }

    @Override
    public void addAnswer() {
        int count = 0;


        System.out.println("In order to add answer, firstly, you should enter Subject and test iD: ");
        System.out.println("Enter subject Name: ");
        String subjectName = DB.scannerStr.nextLine();

        if (!checkSubjectName(subjectName)) {
            System.out.println("Subject not found");
            return;
        }

        for (Test test : DB.testList) {
            if(test.getBelongedSubject().equalsIgnoreCase(subjectName)){
                System.out.println(test);
            }
        }
        System.out.println("Enter test id:");
        int testId = DB.scannerInt.nextInt();

        if (!checkTestId(testId)) {
            System.out.println("Test not found!!!");
            return;
        }

        for (Answer answer : DB.answerList) {
            if(answer.getTestId() == testId){
                count++;
            }
        }

        while (true) {
            System.out.println("Enter answer body (0=> Back): ");
            String body = DB.scannerStr.nextLine();
            if (body.equals("0")){
                break;
            }
            Answer answer = new Answer(count++, testId, body);
        }
        System.out.println("Answer(s) successfully added!!!");

    }

    @Override
    public void updateAnswer() {

        System.out.println("In order to add answer, firstly, you should enter Subject and test iD: ");
        System.out.println("Enter subject Name: ");
        String subjectName = DB.scannerStr.nextLine();

        if (!checkSubjectName(subjectName)) {
            System.out.println("Subject not found");
            return;
        }

        for (Test test : DB.testList) {
            if(test.getBelongedSubject().equalsIgnoreCase(subjectName)){
                System.out.println(test);
            }
        }
        System.out.println("Enter test id:");
        int testId = DB.scannerInt.nextInt();

        if (!checkTestId(testId)) {
            System.out.println("Test not found!!!");
            return;
        }

        for (Answer answer : DB.answerList) {
            if(testId == answer.getTestId()){
                System.out.println(answer);
            }
        }

        System.out.println("Enter answer Id: ");
        int answerId = DB.scannerInt.nextInt();

        if (!checkAnswerId(answerId)) {
            System.out.println("Answer Not Found!!");
            return;
        }

        for (Answer answer : DB.answerList) {
            if(answer.getId() == answerId){
                System.out.println("Enter new body: ");
                String body = DB.scannerStr.nextLine();

                answer.setBody(body);
                System.out.println("Successfully updated!!!");
                break;
            }
        }

    }

    @Override
    public void deleteAnswer() {
        System.out.println("In order to add answer, firstly, you should enter Subject and test iD: ");
        System.out.println("Enter subject Name: ");
        String subjectName = DB.scannerStr.nextLine();

        if (!checkSubjectName(subjectName)) {
            System.out.println("Subject not found");
            return;
        }


        for (Test test : DB.testList) {
            if(test.getBelongedSubject().equalsIgnoreCase(subjectName)){
                System.out.println(test);
            }
        }
        System.out.println("Enter test id:");
        int testId = DB.scannerInt.nextInt();

        if (!checkTestId(testId)) {
            System.out.println("Test not found!!!");
            return;
        }


        for (Answer answer : DB.answerList) {
            if(testId == answer.getTestId()){
                System.out.println(answer);
            }
        }

        System.out.println("Enter answer Id: ");
        int answerId = DB.scannerInt.nextInt();

        if (!checkAnswerId(answerId)) {
            System.out.println("Answer Not Found!!");
            return;
        }

        for (Answer answer : DB.answerList) {
            if(answer.getId() == answerId){

                DB.answerList.remove(answerId);
                System.out.println("Successfully deleted!!!");
                break;
            }
        }

    }


    public boolean checkAnswerId(int id){
        for (Answer answer : DB.answerList) {
            if(id == answer.getId()){
                return true;
            }
        }
        return false;
    }

    public boolean checkTestId(int id){
        for (Test test : DB.testList) {
            if(id == test.getId()){
                return true;
            }
        }
        return false;
    }

    public boolean checkSubjectName(String name){
        for (Subject subject : DB.subjectList) {
            if(subject.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
