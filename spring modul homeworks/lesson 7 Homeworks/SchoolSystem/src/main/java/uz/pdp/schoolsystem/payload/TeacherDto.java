package uz.pdp.schoolsystem.payload;

import lombok.Data;

@Data
public class TeacherDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer subjectId;

}
