package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Order {
    private User client;
    //
    private List<Cart> clientCart;
    //
    private Status status;
    private User chief;
    private User supplier;
    private LocalDateTime dateTime;
    //
    private double totalPrice;
    private PayType payType;

}
