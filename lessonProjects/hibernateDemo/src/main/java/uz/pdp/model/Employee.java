package uz.pdp.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private double salary;

}
