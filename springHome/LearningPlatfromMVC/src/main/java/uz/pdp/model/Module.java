package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Module {
    private Integer id;
    private Integer courseId;
    private String title;
    private Integer orderNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
