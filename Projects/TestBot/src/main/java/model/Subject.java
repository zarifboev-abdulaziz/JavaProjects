package model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.base.BaseModel;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subject extends BaseModel {
    String name;
}
