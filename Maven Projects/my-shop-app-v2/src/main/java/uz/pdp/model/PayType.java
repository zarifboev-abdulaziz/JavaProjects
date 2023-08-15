package uz.pdp.model;

import uz.pdp.model.abs.AbsEntity;

public class PayType extends AbsEntity {
    private String name;
    private double commissionFee; // in percentage (foiz)

    public PayType() {
    }

    public PayType(String name, double commissionFee) {
        this.name = name;
        this.commissionFee = commissionFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCommissionFee() {
        return commissionFee;
    }

    public void setCommissionFee(double commissionFee) {
        this.commissionFee = commissionFee;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", Commission Fee: " + commissionFee;
    }
}
