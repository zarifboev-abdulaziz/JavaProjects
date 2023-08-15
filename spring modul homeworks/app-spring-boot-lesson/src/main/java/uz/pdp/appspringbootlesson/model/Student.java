package uz.pdp.appspringbootlesson.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.util.Date;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phoneNumber;
}
