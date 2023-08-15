package uz.pdp.websocketdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue()
    private Integer id;

    String chatId;

    @ManyToOne
    User sender;

    @ManyToOne
    User receiver;


    public ChatRoom(String chatId, User sender, User receiver) {
        this.chatId = chatId;
        this.sender = sender;
        this.receiver = receiver;
    }
}
