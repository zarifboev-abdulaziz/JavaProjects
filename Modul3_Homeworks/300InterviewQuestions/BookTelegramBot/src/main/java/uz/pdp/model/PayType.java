package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PayType {
    private UUID id = UUID.randomUUID();
    private String name;
    private double commissionFee;

    public PayType(String name, double commissionFee) {
        this.name = name;
        this.commissionFee = commissionFee;
    }
}
