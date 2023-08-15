package uz.pdp.online;

import uz.pdp.online.model.User;

import java.util.Arrays;
import java.util.Scanner;

//2-vazifaga qo'shimcha tarzda base package ichida Main classini va unda main methodini yarating.
//5 ta userdan  iborat User toifasidagi massiv yarating va massiv elementlarini ekranga chiqazing.
//Properties

public class Main {
    public static void main(String[] args) {

        Scanner scanStr = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);

        User user1 = new User();
        User[] userList = new User[5];

/*
        for (int i = 0; i < userList.length; i++) {

            if(userList[i] != null && i == userList.length-1){
                System.out.println("List is full.");
            }


            if(userList[i] != null){
                User userName = new User(scan.nextLine()) ;
                userList[i] = userName;
            } else {
                continue;
            }

        }



        System.out.print("UserList: ");
        for (int i = 0; i < userList.length; i++) {
            System.out.print(" " + userList[i].name);
        }
   */


        while(true) {
        System.out.println("1.Add User\n2.Show User List\n3.Register\n4.ChangePassword\n5.My Profile\n6.Exit");
        System.out.print("Enter your option: ");
        int option = scanInt.nextInt();
        switch (option) {
            case 1: {
                for (int i = 0; i < userList.length; i++) {

                    if(userList[i] != null && i == userList.length-1){
                        System.out.println("List is full.");
                        break;
                    }

                    if(userList[i] == null){
                        System.out.print("Enter user Name: ");
                        User userName = new User(scanStr.nextLine()) ;
                        userList[i] = userName;
                        System.out.println("User Successfully added.");
                        break;
                    } else {
                        continue;
                    }

                }
            }break;

            case 2: {
                System.out.println("UserList: ");
                for (int i = 0; i < userList.length; i++) {
                    if(userList[i] == null){
                        System.out.println(i + 1 + "- User = " + "Bo'sh joy");
                    } else {
                        System.out.println(i + 1 + "- User = " + userList[i].name);
                    }

                }
            }break;

            case 3: {
                System.out.println("Enter your email: ");
                String email = scanStr.nextLine();
                System.out.println("Enter your password: ");
                String password1 = scanStr.nextLine();
                user1.email = email;
                user1.setPassword(password1);

            }
            break;

            case 4: {
                user1.changePassword();

            }
            break;

            case 5: {
                System.out.println("User email : " + user1.email);
                System.out.println("User password : " + user1.getPassword());
            }
            break;

            case 6: {}break;
            default: {
                System.out.println("Wrong option.");
            }break;
        }
        if(option == 6){break;}
    }




    }
}
