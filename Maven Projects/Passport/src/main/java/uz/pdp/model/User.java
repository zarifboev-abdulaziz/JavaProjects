package uz.pdp.model;

//Yunus Boliyev 14.12.2021, 16:46

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.enums.BotState;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id=UUID.randomUUID();
    private String chatId;
    private String username;
    private String serialNumber;
    private BotState state;

}
