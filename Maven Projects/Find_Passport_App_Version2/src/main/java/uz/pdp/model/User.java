package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.enums.Round;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String secondPhoneNumber;
    private String email;
    private String username;
    private String chatId;
    private Round round;



    public User(String firstName, String lastName, String phoneNumber, String secondPhoneNumber, String email, String username, Round round) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.secondPhoneNumber = secondPhoneNumber;
        this.email = email;
        this.username = username;
        this.round = round;
    }
}
