package model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.base.BaseModel;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Answer extends BaseModel {
    UUID questionId;
    boolean isCorrect;
    String text;
}
