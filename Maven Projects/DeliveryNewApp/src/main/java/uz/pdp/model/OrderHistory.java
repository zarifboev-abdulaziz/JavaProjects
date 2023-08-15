package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class OrderHistory {
    private int id;
    private User client;
    private List<Cart> clientCart;
    private User chief;
    private User supplier;
    private double totalSum;
    private PayType payType;

    @Override
    public String toString() {
        String foods = "";
        for (Cart cart : clientCart) {
            foods += cart.getFood().getName() + "; ";
        }
        return "Client: " + client.getFullName() + "Cart: " + foods + " | Chef: "
                + chief.getFullName() + "Supplier: " + supplier.getFullName() + " | Total sum: " + totalSum
                + " | PayType: " + payType.getName();
    }
}
