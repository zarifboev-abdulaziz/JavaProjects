package uz.pdp.service;

import uz.pdp.DB;
import uz.pdp.model.Subject;
import uz.pdp.service.interfaces.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    @Override
    public void subjectMenu() {
        while (true){
            System.out.print("1.Show Subject List\n2.Add Subject\n3.Update Subject\n4.Delete Subject\n5.Back\n=>");
            int subjectOption = DB.scannerInt.nextInt();
            switch (subjectOption){
                case 1:
                    showSubjectList();
                    break;
                case 2:
                    addSubject();
                    break;
                case 3:
                    updateSubject();
                    break;
                case 4:
                    deleteSubject();
                    break;

                case 5: return;
                default:
                    System.out.println("Wrong option");
                    break;
            }

        }
    }

    @Override
    public void showSubjectList() {
        if(DB.subjectList.size() == 0){
            System.out.println("List is empty");
            return;
        }

        for (Subject subject : DB.subjectList) {
            System.out.println(subject);
        }
    }

    @Override
    public void addSubject() {
        int id = (int)(Math.random()*10000);

        System.out.println("Enter subject Name: ");
        String name = DB.scannerStr.nextLine();

        boolean isFound = true;
        for (Subject subject : DB.subjectList) {
            if(subject.getName().equals(name)){
                isFound = false;
                break;
            }
        }

        System.out.println("Enter price of Subject: ");
        double price = DB.scannerInt.nextDouble();

        System.out.println("Enter score per Test: ");
        int scorePerTest = DB.scannerInt.nextInt();

        if(isFound){
            Subject subject = new Subject(id, name, price, 0, 0, scorePerTest);
            DB.subjectList.add(subject);
            System.out.println("Successfully added!!!");
        }else {
            System.out.println("This subject exists!!!");
        }

    }

    @Override
    public void updateSubject() {
        if(DB.subjectList.size() == 0){
            System.out.println("List is empty");
            return;
        }
        showSubjectList();

        System.out.println("Enter id of subject: ");
        int inputId = DB.scannerInt.nextInt();

        boolean isFound = false;
        for (Subject subject : DB.subjectList) {
            if(subject.getId() == inputId){
                isFound = true;

                System.out.println("Enter new name of subject: ");
                String name = DB.scannerStr.nextLine();

                System.out.println("Enter new price of subject: ");
                double price = DB.scannerInt.nextDouble();

                subject.setName(name);
                subject.setPrice(price);
                System.out.println("Successfully updated\n");
            }
        }

        if(!isFound) System.out.println("Subject not found\n");
    }

    public boolean checkId(int id){
        for (Subject subject : DB.subjectList) {
            if(subject.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteSubject() {
        if(DB.subjectList.size() == 0){
            System.out.println("List is empty");
            return;
        }
        showSubjectList();
        System.out.println("Enter subject id to delete: ");
        int inputId = DB.scannerInt.nextInt();

        if (!checkId(inputId)) {
            System.out.println("Subject not found!!!\n");
            return;
        }

        for (Subject subject : DB.subjectList) {
            if(subject.getId() == inputId){
                DB.subjectList.remove(subject);
                System.out.println("Successfully deleted!!!\n");
                break;
            }
        }
    }
}
