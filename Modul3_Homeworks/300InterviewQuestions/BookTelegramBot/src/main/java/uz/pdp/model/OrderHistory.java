package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class OrderHistory {
    private UUID id = UUID.randomUUID();
    private User user;
    private Book book;
    private PayType payType;
    private LocalDateTime dateTime;

    public OrderHistory(User user, Book book, PayType payType, LocalDateTime dateTime) {
        this.user = user;
        this.book = book;
        this.payType = payType;
        this.dateTime = dateTime;
    }
}
