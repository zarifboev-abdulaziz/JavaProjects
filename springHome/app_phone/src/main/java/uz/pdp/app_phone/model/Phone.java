package uz.pdp.app_phone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Phone {
    private Integer id;
    private String model;
    private String macAddress;
    private Integer phoneNumber;

}
