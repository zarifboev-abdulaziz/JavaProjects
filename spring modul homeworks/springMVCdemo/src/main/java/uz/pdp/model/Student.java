package uz.pdp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    int id;
    String firstName;
    String lastName;
    int birthYear;

}
