package uz.pdp.chatdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatRoom {
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
