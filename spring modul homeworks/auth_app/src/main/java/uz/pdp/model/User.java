package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String password;

    public User(String firstName, String lastName, String userName, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
