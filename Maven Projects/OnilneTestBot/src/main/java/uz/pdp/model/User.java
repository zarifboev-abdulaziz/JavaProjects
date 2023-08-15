package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.enums.Role;
import uz.pdp.model.enums.State;

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
    private Role role = Role.STUDENT;


    public User(String firstName, String lastName, String phoneNumber, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }
}
