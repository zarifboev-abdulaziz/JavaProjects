package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.Lesson;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ModuleDto {
    private Integer id;
    private Integer courseId;
    private String title;
    private Integer orderNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<LessonDto> lessons;

}
