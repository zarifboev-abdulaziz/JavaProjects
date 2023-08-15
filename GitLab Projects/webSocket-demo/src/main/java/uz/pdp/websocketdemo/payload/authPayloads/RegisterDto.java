package uz.pdp.websocketdemo.payload.authPayloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String fullName;
    private String email;
    private String password;

}
