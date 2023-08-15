package uz.pdp.springsecuritydemocrud.payload.authPayloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String fullName;
    @Email
    private String email;
    @NotNull
    private String password;

}
