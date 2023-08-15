package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Lesson {
    private Integer id;
    private Integer moduleId;
    private String title;
    private Integer orderNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
