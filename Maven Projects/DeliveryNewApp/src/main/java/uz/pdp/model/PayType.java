package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayType {
    private int id;
    private String name;
    private double commissionFee;

    @Override
    public String toString() {
        return "Name: " + name + " | ID: " + id + " | CommissionFee: " + commissionFee;
    }
}
