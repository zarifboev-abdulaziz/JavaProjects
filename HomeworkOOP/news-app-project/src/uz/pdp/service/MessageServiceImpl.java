package uz.pdp.service;

import uz.pdp.model.User;
import uz.pdp.model.enums.MessageStatus;
import uz.pdp.model.message.Message;
import uz.pdp.service.interfaceSer.MessageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class MessageServiceImpl implements MessageService {

    static List<Message> messages = new ArrayList<>();

    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerDou = new Scanner(System.in);

    @Override
    public void sendMessage(User journalist, Collection<User> journalistList) {

        for (User user : journalistList) {
            if (journalist.equals(user))
                continue;
            System.out.println("id: " + user.getId() + "\n" +
                    "full name: " + user.getFullName() + "\n" +
                    "email: " + user.getEmail() + "\n" +
                    "=====================================");
        }

        System.out.println("Enter journalist or admin id");
        int userId = scannerInt.nextInt();
        User selectedUser = journalistList.stream()
                .filter(user1 -> user1.getId() == userId)
                .findFirst().orElse(null);

        if (selectedUser != null) {
            messageList(selectedUser, journalist);
            System.out.println("Enter title");
            String title = scannerStr.nextLine();
            System.out.println("Enter description");
            String body = scannerStr.nextLine();

            Message message = new Message((int) (Math.random() * 10000), MessageStatus.UNREAD, title, body, journalist, selectedUser);
            messages.add(message);
            System.out.println("Successfully send");
            return;
        }
        System.out.println("Journalist or admin ot found");

    }

    @Override
    public void messageUser(User journalist, Collection<User> journalistList) {
        System.out.println("Your connections");
        for (User user : journalistList) {
            for (Message message : messages) {
                if (user.equals(message.getSender()) && journalist.equals(message.getReciever())
                        && !user.equals(journalist) || user.equals(message.getReciever())
                        && journalist.equals(message.getSender()) && !user.equals(journalist)) {
                    System.out.println("id   : " + user.getId() + ", User : " + user.getFullName());
                    break;
                }
            }
        }

        System.out.println("Enter userid see message");
        int userid = scannerInt.nextInt();
        for (User user : journalistList) {
            for (Message message : messages) {
                if (user.equals(message.getSender()) && user.getId() == userid &&
                        message.getReciever().equals(journalist)) {
                    System.out.println("Sender : " + user.getFullName());
                    System.out.println("\t\t\t\tReciever : " + journalist.getFullName());
                    System.out.println("Message : " + message.getTitle() + ", " + message.getBody());
                    message.setStatus(MessageStatus.READ);
                } else if (user.equals(message.getReciever())
                        && user.getId() == userid
                        && message.getSender().equals(journalist)) {
                    System.out.println("Sender : " + journalist.getFullName());
                    System.out.println("\t\t\t\tReciever : " + user.getFullName());
                    System.out.println("Message : " + message.getTitle() + ", " + message.getBody());
                    message.setStatus(MessageStatus.READ);
                }
            }
        }

    }

    public void messageList(User user, User journalist) {
        for (Message message : messages) {
            if (user.equals(message.getSender()) && journalist.equals(message.getReciever()) &&
                    message.getStatus().equals(MessageStatus.READ)) {
                System.out.println("Sender : " + user.getFullName());
                System.out.println("\t\t\t\tReciever : " + journalist.getFullName());
                System.out.println("Message : " + message.getTitle() + ", " + message.getBody());
            } else if (user.equals(message.getReciever()) && journalist.equals(message.getSender()) &&
                    message.getStatus().equals(MessageStatus.READ)) {
                System.out.println("Sender : " + journalist.getFullName());
                System.out.println("\t\t\t\tReciever : " + user.getFullName());
                System.out.println("Message : " + message.getTitle() + ", " + message.getBody());
            }
        }

    }


}
