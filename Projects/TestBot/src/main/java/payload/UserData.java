package payload;

import utill.enums.BotState;
import utill.enums.Language;
import utill.enums.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserData {
    BotState state;
    Language language;
    Role role;
}
