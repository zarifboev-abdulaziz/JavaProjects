package uz.onlineBank.bank;

import uz.onlineBank.enums.TransactionTypes;

import java.time.LocalDate;

public class Transaction {
    //Properties
    private Integer id;
    private Integer fromUserId;
    private Integer toUserId;
    private double amount;
    private TransactionTypes type;
    private LocalDate date;

    //Constructor
    public Transaction() {
    }

    public Transaction(Integer id, Integer fromUserId, Integer toUserId, double amount, TransactionTypes type, LocalDate date) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    //Getter and Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionTypes getType() {
        return type;
    }

    public void setType(TransactionTypes type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    //to String

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", amount=" + amount +
                ", type=" + type +
                ", date=" + date +
                '}';
    }
}
