package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.enums.Role;

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
    private Role role;
    private String chatId;
    private List<Book> myBooks = new ArrayList<>();

}
