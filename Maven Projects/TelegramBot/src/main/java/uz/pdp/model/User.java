package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.enums.State;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String chatId;
    private State state;
    private int newsPage = 0;
    private Integer currentMessageId = null;
    private List<CustomMessage> myMessageList = new ArrayList<>();


    public User(String firstName, String lastName, String phoneNumber, String username, State state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.state = state;
    }
}
