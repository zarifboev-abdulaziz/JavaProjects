package uz.pdp.service;

import com.sun.deploy.trace.SocketTraceListener;
import uz.pdp.Store;
import uz.pdp.model.Subject;
import uz.pdp.service.interfaceServis.SubjectServis;

import java.awt.*;
import java.util.Scanner;

public class SubjectServisImpl implements SubjectServis {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public void showSubjectMenu() {

        while (true){
            System.out.println("1.showSubjectList\n2.addSubject\n3.updateSubject\n4.deleteSubject\n5.Back");
            int subjectOption = scannerInt.nextInt();
            switch (subjectOption){
                case 1:
                    showSubjectList();
                    break;
                case 2:
                    addSubject();
                    break;
                case 3: break;
                case 4: break;
                case 5: return;
                default:
                    System.out.println("Wrong option!");
                    break;
            }



        }

    }

    @Override
    public void showSubjectList() {
        if(Store.subjectList.size() == 0){
            System.out.println("List is empty!!!");
            return;
        }

        for (Subject subject : Store.subjectList) {
            System.out.println(subject.toString());
        }
    }

    @Override
    public void addSubject() {

        System.out.println("Enter name of the Subject: ");
        String name = scannerStr.nextLine();

        System.out.println("Enter price of the Subject: ");
        double price = scannerInt.nextDouble();


        Subject subject = new Subject((int)(Math.random()*10000), name, 0,0, 5,price );
        System.out.println("Subject Successfully added!!!");

        Store.subjectList.add(subject);

    }

    @Override
    public void updateSubject() {
        showSubjectList();
        System.out.println("Enter Subject id: ");
        int inputId = scannerInt.nextInt();

        boolean isFound = false;
        for (Subject subject : Store.subjectList) {
            if(subject.getId() == inputId){
                System.out.println("Enter new Name of the Subject: ");
                String newName = scannerStr.nextLine();

                System.out.println("Enter new price: ");
                double newPrice =scannerInt.nextDouble();

                subject.setName(newName);
                subject.setPrice(newPrice);
                isFound = true;
                System.out.println("Successfully updated");
            }
        }
        if(!isFound){
            System.out.println("Subject not Found!!!");
        }

    }

    @Override
    public void deleteSubject() {
        showSubjectList();
        System.out.println("Enter Subject id: ");
        int inputId = scannerInt.nextInt();

        boolean isFound = false;
        for (Subject subject : Store.subjectList) {
            if(subject.getId() == inputId){
                Store.subjectList.remove(subject);
                isFound = true;
                System.out.println("Succesfully deleted!!!");
                break;
            }
        }
        if(!isFound){
            System.out.println("Subject not Found!!!");
        }

    }
}
