package uz.pdp.countryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {
    private Integer id;
    private String name;
    private String continentName;
}
