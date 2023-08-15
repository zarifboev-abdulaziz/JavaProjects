package uz.pdp.model;

import uz.pdp.model.abs.AbsEntity;
import uz.pdp.model.abs.User;

import java.util.List;

public class OrderHistory extends AbsEntity {
    private User customer;
    private List<OrderItem> items;
    private double price;
    private double commissionFeeSum;
    private PayType payType;

    public OrderHistory() {
    }

    public OrderHistory(User customer, List<OrderItem> items, double price, double commissionFeeSum, PayType payType) {
        this.customer = customer;
        this.items = items;
        this.price = price;
        this.commissionFeeSum = commissionFeeSum;
        this.payType = payType;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCommissionFeeSum() {
        return commissionFeeSum;
    }

    public void setCommissionFeeSum(double commissionFeeSum) {
        this.commissionFeeSum = commissionFeeSum;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "customer=" + customer +
                ", items=" + items +
                ", price=" + price +
                ", commissionFeeSum=" + commissionFeeSum +
                ", payType=" + payType +
                '}';
    }
}
