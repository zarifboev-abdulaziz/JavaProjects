package uz.pdp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class LearningMaterial {
    private Integer id;
    private Integer lessonId;
    private String title;
    private String information;
    private Integer orderNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}
