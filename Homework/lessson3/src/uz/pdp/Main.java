package uz.pdp;

import uz.pdp.model.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        while (true){
            System.out.println("1.Log in\n2.Register\n3.Exit");
            int option =scannerInt.nextInt();
            switch (option){
                case 1:
                    break;
                case 2:
                    break;

                case 3: return;
                default:
                    System.out.println("Wrong option");
                    break;
            }





        }
    }
}
