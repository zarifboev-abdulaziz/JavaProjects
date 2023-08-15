package uz.onlineBank.service;

import uz.onlineBank.bank.Transaction;
import uz.onlineBank.enums.RolePerson;
import uz.onlineBank.enums.TransactionTypes;
import uz.onlineBank.person.Employee;
import uz.onlineBank.person.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Registrations {
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerDouble = new Scanner(System.in);

    //Lists
    List<Transaction> transactions = new ArrayList<>();
    int transactionId = 1;
    List<User> users = new ArrayList<>();
    int userId = 1;
    List<Employee> employees = new ArrayList<>();
    int employeeId = 1;

    //todo:: Admin should be  created
    public Registrations(){
        Employee employee = new Employee(employeeId, "Admin", "991234567", "1212", RolePerson.ADMIN) ;
        employeeId++;

        employees.add(employee);
    }


    //todo::user registration
    public void signUp(){
        System.out.println("Enter your name: ");
        String name = scannerStr.nextLine();

        System.out.println("Enter your phone: ");
        String phone = scannerStr.nextLine();

        if(!checkPhone(phone)){
            System.out.println("Enter password: ");
            String password = scannerStr.nextLine();

            User user = new User(userId, name, phone, password, RolePerson.USER);
            userId++;

            users.add(user);
            System.out.println("User created Successfully");
        } else {
            System.out.println("Phone already exists");
        }
    }

    //todo::add Employee procedure
    public void addEmployee(Employee employee){
        if(employee.getRole().equals(RolePerson.ADMIN)){
            System.out.println("Enter name: ");
            String name = scannerStr.nextLine();

            System.out.println("Enter phone: ");
            String phone = scannerStr.nextLine();

            if(!employeePhone(phone)){
                System.out.println("Enter password: ");
                String password = scannerStr.nextLine();

                Employee employee1 = new Employee(employeeId, name, phone, password, RolePerson.EMPLOYEE);
                employeeId++;

                employees.add(employee1);
                System.out.println("Employee created");

            } else {
                System.out.println("Phone already exists.");
                return;
            }

        } else {
            System.out.println("Permission is denied");
        }


    }




    //Login procedure
    //todo::employee login
    public Employee signInEmployee(){

        System.out.println("Enter phone: ");
        String phone = scannerStr.nextLine();

        System.out.println("Enter password: ");
        String password = scannerStr.nextLine();

        for (Employee employee : employees) {
            if(employee.getPhone().equalsIgnoreCase(phone) && employee.getPassword().equalsIgnoreCase(password)){
               return employee;
            }
        }
        return null;

    }

    //todo::user login procedure

    public User sigIn(){
        System.out.println("Enter phone: ");
        String phone = scannerStr.nextLine();

        System.out.println("Enter passsword: ");
        String password = scannerStr.nextLine();

        for (User user : users) {
            if( user.getPhone().equalsIgnoreCase(phone) && user.getPassword().equalsIgnoreCase(password) ){
              return user;
            }
        }
        return null;
    }

    // todo::Show Lists function
    public void employeeList(){
        employees.forEach(employee -> System.out.println(employee.toString()));
    }

    //todo:: Show User List
    public void userList(){
        users.forEach(user -> System.out.println(user.toString()));
    }

    //todo:: Transaction List
    public void transactionList(){
        for (Transaction transaction : transactions) {
            for (User user : users) {
                if(user.getId() == transaction.getFromUserId()){
                    System.out.println("Transaction{" +
                            "id=" + transaction.getId() +
                            ", fromUserId=" + user.getName() +
                            ", amount=" + transaction.getAmount() +
                            ", type=" + transaction.getType() +
                            ", date=" + transaction.getDate() +
                            '}');
                }
            }
        }
    }




    public boolean employeePhone(String phone){

        for (Employee employee : employees) {
            if(employee.getPhone().equalsIgnoreCase(phone)){
                return true;
            }
        }
        return false;

    }



    public boolean checkPhone(String phone) {

        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getPhone().equalsIgnoreCase(phone)){
                return true;
            }
        }
        return false;



    }























    //todo::User Menu
    public void balance(User user){
        for (User user1 : users) {
            if(user1 == user){
                System.out.println("Balance : " + user1.getBalance());
                break;
            }
        }
    }

    public void sendMoney(User user){
        System.out.println("Enter cardNumber:  ");
        String cardNumber = scannerStr.nextLine();

        System.out.println("Enter amount: ");
        double amount = scannerDouble.nextDouble();

        if(user.getBalance() >= amount && amount > 0){
            LocalDate date = LocalDate.now();
            user.reduceMoney(user, amount);
            Transaction transaction = new Transaction(transactionId, user.getId(), null, amount, TransactionTypes.SEND, date );
            transactionId++;
            transactions.add(transaction);


        }else {
            System.out.println("Invalid amount");
        }
    }

    public void receiveMoney(User user){
        System.out.println("Enter money: ");
        double amount = scannerDouble.nextDouble();

        if(amount > 0) {
            user.reduceMoney(user, amount);

            LocalDate date = LocalDate.now();
            Transaction transaction = new Transaction(transactionId, user.getId(), null, amount, TransactionTypes.RECEIVE, date);
            transactionId++;
            transactions.add(transaction);
            System.out.println("Money successfully received ");
        } else {
            System.out.println("Invalid amount");
        }
    }

    public void transactionHistory(User user){
        for (Transaction transaction : transactions) {
            if(transaction.getFromUserId() == user.getId()){
                System.out.println("Transaction{" +
                        "id=" + transaction.getId() +
                        ", fromUserId=" + user.getName() +
                        ", amount=" + transaction.getAmount() +
                        ", type=" + transaction.getType() +
                        ", date=" + transaction.getDate() +
                        '}');
            }
        }

    }

    public void addPlasticCard(User user){
        System.out.println("Texnik ishlar olib borilyabdi");
    }

    public void notification(User user){
        System.out.println("Texnik ishlar olib borilyabdi");
    }


}
