package uz.pdp;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
Student[] studentList = new Student[5];
        System.out.println("1=> O'quvchi qo'shish\n2=> Ro'yxatni ko'rish");

        Scanner scanint = new Scanner(System.in);
        Scanner scanstr = new Scanner(System.in);
        int option = scanint.nextInt();

        switch(option) {
            case 1: {

               Student student1 = new Student("Abror", "Ergashev", 25);
                for (int i = 0; i < studentList.length; i++) {
                    if(studentList[i] != null){
                        continue;
                    } else {
                        studentList[i] = student1;
                        break;

                    }

                }
            }break;

            case 2:{
                System.out.println(Arrays.toString(studentList));
            }break;


        }




    }
}
