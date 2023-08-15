package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer roleId;
    private Double balance;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
