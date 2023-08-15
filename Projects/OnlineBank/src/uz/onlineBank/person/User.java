package uz.onlineBank.person;

import uz.onlineBank.enums.RolePerson;

public class User extends Person{
    //Properties
    private double balance;
    private String cardNumber;

    public void reduceMoney(User user, double amount){
        user.setBalance(user.getBalance() - amount);
    }

    //Constructors

    public User() {
    }

    public User(Integer id, String name, String phone, String password, RolePerson role) {
        super(id, name, phone, password, role);
    }

    public User(double balance, String cardNumber) {
        this.balance = balance;
        this.cardNumber = cardNumber;
    }

    public User(Integer id, String name, String phone, String password, RolePerson role, double balance, String cardNumber) {
        super(id, name, phone, password, role);
        this.balance = balance;
        this.cardNumber = cardNumber;
    }

    //Getter and Setter


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    //to String


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", balance=" + balance +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
