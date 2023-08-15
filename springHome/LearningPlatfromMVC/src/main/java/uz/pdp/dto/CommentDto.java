package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CommentDto {
    private Integer id;
    private Integer lessonId;
    private Integer userId;
    private String body;
    private String userName;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
