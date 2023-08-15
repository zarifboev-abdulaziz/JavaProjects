package uz.onlineBank.template;

import uz.onlineBank.person.Employee;
import uz.onlineBank.person.User;
import uz.onlineBank.service.Registrations;

import java.util.Scanner;

public class FrontEndAction {
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);


    public void frontEnd(){
        Registrations registrations = new Registrations();

        boolean active = true;

        while (active){
            System.out.println("1.SignIn. 2.SighUp. 3.Admin. 4.Exit");
            int option = scannerInt.nextInt();
            switch (option){
                case 1:{
                    User user = registrations.sigIn();
                    if(user != null){
                        boolean userMenu = true;
                        while(userMenu){
                            System.out.println("1.See balance. 2.SendMoney. 3.Receive Money. 4.Transaction History. 5.Add Plastic. 6.Notification. 7.Logout");
                            int userOption = scannerInt.nextInt();
                            switch (userOption){
                                case 1:{
                                    registrations.balance(user);
                                    break;}
                                case 2: {
                                    registrations.sendMoney(user);
                                    break;}
                                case 3: {
                                    registrations.receiveMoney(user);
                                    break;}
                                case 4: {
                                    registrations.transactionHistory(user);
                                    break;}
                                case 5:{
                                    registrations.addPlasticCard(user);
                                     break;}
                                case 6:{
                                    registrations.notification(user);
                                    break;}
                                case 7: {
                                    userMenu = false;
                                     break;}
                                default: {
                                    System.out.println("Wrong option");
                                    break;
                                }

                            }



                        }
                    } else {
                        System.out.println("User not found");
                    }
                    break;
                }

                case 2:{
                    registrations.signUp();
                    break;
                }

                case 3: {
                    Employee employee = registrations.signInEmployee();
                    if (employee != null) {
                        boolean adminMenu = true;
                        while (adminMenu) {
                            System.out.println("1.Add Employee. 2.User List. 3.TransactionList. 4.Employee List. 5.Logout");
                            int optionAdmin = scannerInt.nextInt();
                            switch (optionAdmin){
                                case 1:{
                                    registrations.addEmployee(employee);
                                }break;

                                case 2:{
                                    registrations.userList();
                                }break;

                                case 3:{
                                    registrations.transactionList();
                                }break;

                                case 4:{
                                    registrations.employeeList();
                                }break;

                                case 5:{
                                    adminMenu = false;
                                }break;

                                default:{
                                    System.out.println("Wrong option");
                                }break;
                            }
                        }

                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                }

                case 4:{
                    active = false;
                }break;

                default: {
                    System.out.println("Wrong option");
                }break;
            }
        }
    }
}
