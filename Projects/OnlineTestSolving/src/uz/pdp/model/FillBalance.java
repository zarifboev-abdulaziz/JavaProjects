package uz.pdp.model;

public class FillBalance {
    private int id;
    private String userName;
    private double amount;
    private String payType;

    public FillBalance() {
    }

    public FillBalance(int id, String userName, double amount, String payType) {
        this.id = id;
        this.userName = userName;
        this.amount = amount;
        this.payType = payType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "FillBalance{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", amount=" + amount +
                ", payType='" + payType + '\'' +
                '}';
    }
}
