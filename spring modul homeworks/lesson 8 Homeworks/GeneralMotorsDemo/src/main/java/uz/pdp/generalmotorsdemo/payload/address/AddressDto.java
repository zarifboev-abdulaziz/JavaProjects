package uz.pdp.generalmotorsdemo.payload.address;

import lombok.Data;

@Data
public class AddressDto {
    private String street;
    private String homeNumber;
    private Integer districtId;

}
