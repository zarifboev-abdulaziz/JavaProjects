package uz.pdp.websocketdemo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.websocketdemo.model.MessageStatus;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto {
    private Integer id;
    private String text;
    private Integer receiverId;
    private Integer senderId;
    private String senderFullName;
    private String receiverFullName;
    private LocalDateTime localDateTime = LocalDateTime.now();
    private MessageStatus messageStatus = MessageStatus.UNREAD;


}
