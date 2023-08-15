package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.LearningMaterial;
import uz.pdp.model.Task;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LessonDto {
    private Integer id;
    private Integer moduleId;
    private String title;
    private Integer orderNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<LearningMaterial> learningMaterials;
    private List<Task> tasks;

}
