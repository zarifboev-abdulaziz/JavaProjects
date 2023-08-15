package model;

import utill.enums.BotState;
import utill.enums.Language;
import utill.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import model.base.BaseModel;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseModel {
    String firstname;
    String lastname;
    String username;
    String chatId;
    String phoneNumber;
    Role role;
    Language language;
    BotState state;
}
