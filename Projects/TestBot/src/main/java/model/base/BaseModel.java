package model.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseModel {
    UUID id;
    {
        boolean isActive = true;
        id=UUID.randomUUID();
    }
    String createdTime;
}
