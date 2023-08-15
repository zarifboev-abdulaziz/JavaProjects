package uz.pdp.task3.payload.address;

import lombok.Data;

@Data
public class AddressDto {
    private String street;
    private String homeNumber;
    private Integer districtId;

}
