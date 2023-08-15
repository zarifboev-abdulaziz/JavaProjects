package uz.pdp.servis;

import uz.pdp.DataBase;
import uz.pdp.model.Card;
import uz.pdp.model.CardTypeNotMatch;
import uz.pdp.model.NotEnoughMoney;
import uz.pdp.model.User;
import uz.pdp.model.enums.CardType;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedMap;

public class UserServiceImpl implements UserService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public User login() {

        System.out.println("Enter your phone number: ");
        String phone = scannerStr.nextLine();

        System.out.println("Enter your passsword: ");
        String password = scannerStr.nextLine();

        for (User user : DataBase.userList) {
            if (user.getPhone().equals(phone) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void register() {
        int id = (int)(Math.random()*10000);

        System.out.println("Enter your Name: ");
        String fullName = scannerStr.nextLine();

        System.out.println("Enter your phone number: ");
        String phone = scannerStr.nextLine();

        System.out.println("ENter your password: ");
        String password = scannerStr.nextLine();

        User user = new User(id, fullName, phone, password);
        DataBase.userList.add(user);
        System.out.println("USer successfully added!!!");

    }

    @Override
    public void userMenu(User user) throws InputMismatchException{
        System.out.println("1.Send Money\n2.Show balance\n3.Fill balance\n4.show my cards\n5.Create Card\n6.Back");
        int option = scannerInt.nextInt();
        switch (option){
            case 1:
                sendMoney(user);
                break;
            case 2:
                showBalance(user);
                break;
            case 3:
                fillBalance(user);
                break;
            case 4:
                myCards(user);
                break;
            case 5:
                createCard(user);
                break;
            case 6:return;
            default:
                System.out.println("wrong option!!!!");
                break;

        }
        userMenu(user);
    }

    @Override
    public void sendMoney(User user) throws InputMismatchException{
        Card receiverCard = null;
        Card senderCard = null;

        System.out.println("Enter phone Number of receiver to send money: ");
        String phoneNumber = scannerStr.nextLine();

        for (Card card : DataBase.cardList) {
            if (card.getOwner().getPhone().equals(phoneNumber)){
                receiverCard = card;
            }
            if (card.getOwner().equals(user)){
                senderCard = card;
            }
        }

        if (senderCard.getCardType() != receiverCard.getCardType()){
            try {
                throw new CardTypeNotMatch();
            } catch (CardTypeNotMatch e) {
                System.err.println(e.getMessage());
                return;
            }
        }

        System.out.println("Enter amount: ");
        double amount = scannerInt.nextDouble();

        if (amount > senderCard.getBalance()){
            try {
                throw new NotEnoughMoney();
            } catch (NotEnoughMoney e) {
                System.err.println(e.getMessage());
                return;
            }
        }

        senderCard.setBalance(senderCard.getBalance() - amount);
        receiverCard.setBalance(receiverCard.getBalance() + amount);
        System.out.println("Transaction successfully done!!!");

    }

    @Override
    public void showBalance(User user) {
        for (Card card : DataBase.cardList) {
            if (card.getOwner().equals(user)){
                System.out.println("Balance: " + card.getBalance());
                break;
            }
        }
    }

    @Override
    public void fillBalance(User user) throws InputMismatchException{
        System.out.println("Enter amount: ");
        double amount = scannerInt.nextDouble();

        for (Card card : DataBase.cardList) {
            if (card.getOwner().equals(user)){
                card.setBalance(amount);
                System.out.println("Successfully done!!!");
                break;
            }
        }

    }

    @Override
    public void myCards(User user) {
        for (Card card : DataBase.cardList) {
            if (card.getOwner().equals(user)){
                System.out.println(card);
                break;
            }
        }
    }

    public void createCard(User user) throws InputMismatchException {
        int id = (int)(Math.random()*10000);

        System.out.println("Enter name of the card: ");
        String name = scannerStr.nextLine();

        System.out.println("Select type of the card from the list below: ");
        System.out.println("1=> UZCARD 2=> HUMO 3=> VISA 4=> UNIONPAY");
        int option = scannerInt.nextInt();
        CardType cardType = null;
        switch (option){
            case 1:
                cardType = CardType.UZCARD;
            break;

            case 2:
                cardType = CardType.HUMO;
                break;
            case 3:
                cardType = CardType.VISA;
                break;
            case 4:
                cardType = CardType.UNIONPAY;
                break;
        }

        Card card = new Card(id, user, 0, cardType);
        DataBase.cardList.add(card);
        System.out.println("Successfully created!!!");

    }
}
