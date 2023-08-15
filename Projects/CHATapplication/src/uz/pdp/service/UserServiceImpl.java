package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.Group;
import uz.pdp.model.Message;
import uz.pdp.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    GroupServiceImpl groupService = new GroupServiceImpl();


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
            System.out.print("1.Send Message\n2.Personal chat\n3.My Groups\n4.Groups\n5.Create group\n6.log out\n=>");
            int option = scannerInt.nextInt();
            switch (option){
                case 1:
                    sendMessage(user);
                    break;
                case 2:
                    personalChat(user);
                    break;
                case 3:
                    groupService.myGroups(user);
                    break;
                case 4:
                    groupService.groups(user);
                    break;
                case 5:
                    groupService.createGroup(user);
                    break;
                case 6:
                    return;

                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }
    }


    public void personalChat(User user) {
        int count = 0;

        Map<String, Message> showOne = new HashMap<>();

        for (Message message : Store.messageList) {
            if(  message.getReceiverUserName().equals(user.getUserName())){
                showOne.put(message.getSenderUserName(), message);
                count++;
            }
        }
        if(count==0){
            System.out.println("NO available messages");
            return;
        }

        for (Message value : showOne.values()) {
            System.out.println("Name: " + value.getSenderName() + ", userName: " + value.getSenderUserName());
        }

        System.out.println("Enter Sender userName to see chat: ");
        String senderUserName = scannerStr.nextLine();

        boolean isFound = false;
        for (Message message : Store.messageList) {
            if(message.getReceiverUserName().equals(senderUserName) && message.getSenderUserName().equals(user.getUserName())){
                isFound = true;
                System.out.println("Me: " + message.getBody());
            }

            if(message.getReceiverUserName().equals(user.getUserName()) && message.getSenderUserName().equals(senderUserName)){
                isFound = true;
                System.out.println(message.getSenderName() + ": " + message.getBody());
            }
        }

        if (!isFound){
            System.out.println("Wrong userNAme!!!");
            return;
        }

        while (true) {
            System.out.println("Type (0=> back):  ");
            String body = scannerStr.nextLine();
            if(body.equals("0")){break;}

            Message message = new Message((int) (Math.random() * 10000), body, user.getUserName(), user.getFullName(), senderUserName);
            Store.messageList.add(message);
        }

        return;

    }

    public void sendMessage(User user){
        int id = (int)(Math.random()*10000);

        System.out.println("Enter receiver UserName: ");
        String receiverUserName = scannerStr.nextLine();

        boolean isFound = false;
        for (User user1 : Store.userList) {
            if((user1.getUserName().equals(receiverUserName)) && !user1.getUserName().equals(user.getUserName())){
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("User not found!!!");
            return;
        }

        System.out.println("Enter message: ");
        String body = scannerStr.nextLine();

        Message message = new Message(id, body, user.getUserName(), user.getFullName(), receiverUserName);
        Store.messageList.add(message);
    }


}
