package uz.pdp.model.payment;

import uz.pdp.model.News;
import uz.pdp.model.payment.PaymentMethod;

public class Transaction {

    private Integer id;
    private double amount;
    private News news;
    private PaymentMethod type;

    public Transaction() {
    }

    public Transaction(Integer id, double amount, News news, PaymentMethod type) {
        this.id = id;
        this.amount = amount;

        this.news = news;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public PaymentMethod getType() {
        return type;
    }

    public void setType(PaymentMethod type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +

                ", news=" + news +
                ", type=" + type +
                '}';
    }
}
