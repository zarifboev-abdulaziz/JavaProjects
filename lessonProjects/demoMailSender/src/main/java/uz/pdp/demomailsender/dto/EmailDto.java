package uz.pdp.demomailsender.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.demomailsender.controller.MailControllerToHr;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    private String to;
    private String subject;
    private String text;

}
