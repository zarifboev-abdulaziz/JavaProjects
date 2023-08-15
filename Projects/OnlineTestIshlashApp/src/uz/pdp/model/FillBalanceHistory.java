package uz.pdp.model;

public class FillBalanceHistory {
    int id;
    String userName;
    String payType;
    double amount;

    public FillBalanceHistory() {
    }


    public FillBalanceHistory(int id, String userName, String payType, double amount) {
        this.id = id;
        this.userName = userName;
        this.payType = payType;
        this.amount = amount;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FillBalanceHistory{" +
                "id=" + id +
                ", userName=" + userName +
                ", payType=" + payType +
                ", amount=" + amount +
                '}';
    }
}
