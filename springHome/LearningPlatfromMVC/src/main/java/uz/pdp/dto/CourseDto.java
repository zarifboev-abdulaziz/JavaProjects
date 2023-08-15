package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.User;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CourseDto {
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<ModuleDto> modules;
    private List<User> mentors;

}
