package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import uz.pdp.model.Author;
import uz.pdp.model.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CourseDto {
    private Integer id;
    private String  name;
    private String  description;
    private boolean isActive;
    private List<Author> authors;

}
