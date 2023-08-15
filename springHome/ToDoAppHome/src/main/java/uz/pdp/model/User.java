package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private double balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
