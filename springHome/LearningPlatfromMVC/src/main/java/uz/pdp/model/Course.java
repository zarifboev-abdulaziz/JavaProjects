
package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
