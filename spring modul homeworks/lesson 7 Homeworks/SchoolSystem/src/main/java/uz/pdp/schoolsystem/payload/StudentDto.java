package uz.pdp.schoolsystem.payload;

import lombok.Data;
import uz.pdp.schoolsystem.model.Groups;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer groupId;

}
