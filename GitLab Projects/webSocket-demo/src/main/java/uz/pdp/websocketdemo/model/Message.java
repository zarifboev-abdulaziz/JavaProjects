package uz.pdp.websocketdemo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
@Entity
public final class Message {
    @Id
    @GeneratedValue()
    private Integer id;
    String text;
    @ManyToOne
    User receiver;
    @ManyToOne
    User sender;

    @Enumerated(EnumType.STRING)
    MessageStatus messageStatus = MessageStatus.UNREAD;
    String chatId;

    @CreationTimestamp
    LocalDateTime localDateTime = LocalDateTime.now();

    public Message(String text, User receiver, User sender, String chatId) {
        this.text = text;
        this.receiver = receiver;
        this.sender = sender;
        this.chatId = chatId;
    }
}

