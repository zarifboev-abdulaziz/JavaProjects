package uz.pdp.lesson2spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Person {
    private Integer id;
    private String fullName;
    private String phoneNumber;
}
