package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.Message;
import uz.pdp.model.User;

import java.util.Scanner;

public class UserServiceImpl implements UserService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public User login() {
        System.out.println("Enter userName: ");
        String userName = scannerStr.nextLine();

        System.out.println("Enter password: ");
        String password = scannerStr.nextLine();

        for (User user : Store.userList) {
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }

        return null;
    }

    @Override
    public void register() {
        int id = (int)(Math.random()*10000);
        String userName = null;

        System.out.println("Enter your full Name: ");
        String fullName = scannerStr.nextLine();

        while (true) {
            System.out.println("Enter your UserName (0 => back):");
            userName = scannerStr.nextLine();
            if(userName.equals("0")){
                return;
            }

            boolean isFound = false;
            for (User user : Store.userList) {
                if (user.getUserName().equals(userName)) {
                    isFound = true;
                    System.out.println("this user Name available. Please enter another");
                }
            }
            if(!isFound){
                break;
            }
        }

        System.out.println("Enter your password");
        String password = scannerStr.nextLine();

        User user = new User(id, fullName, userName, password);
        Store.userList.add(user);
        System.out.println("Successfully created");

    }


    public void userMenu(User user){
        while (true) {
            System.out.println("1.Send Message\n2.Receive message\n=>");
            int option = scannerInt.nextInt();
            switch (option){
                case 1:
                    sendMessage(user);
                    break;
                case 2:
                    receiveMessage(user);
                    break;

                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }
    }

    public void receiveMessage(User user) {
        for (Message message : Store.messageList) {
            if(message.getReceiverUserName().equals(user.getUserName())){
                System.out.println("Name: " + message.getSenderName() + ", id: " + message.getSenderId());
            }
        }

        System.out.println("Enter Sender id to see chat: ");
        int senderId = scannerInt.nextInt();

        for (Message message : Store.messageList) {
            if(message.getReceiverUserName().equals(user.getUserName()) && message.getSenderId() == senderId){
                System.out.println(message.getSenderName() + ": " + message.getBody());
            }
        }

        return;

    }

    public void sendMessage(User user){
        int id = (int)(Math.random()*10000);

        System.out.println("Enter receiver UserName: ");
        String receiverUserName = scannerStr.nextLine();

        for (User user1 : Store.userList) {
            if (!user1.getUserName().equals(receiverUserName)){
                System.out.println("UserName not found!!!");
                return;
            }
        }

        System.out.println("Enter message: ");
        String body = scannerStr.nextLine();

        Message message = new Message(id, body, user.getId(), user.getFullName(), receiverUserName);
        Store.messageList.add(message);
    }

}
