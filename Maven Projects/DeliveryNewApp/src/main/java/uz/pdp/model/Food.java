package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.enums.Type;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Food {
    private int productCode;
    private String name;
    private double price;
    private Type type;

    @Override
    public String toString() {
        return "Product code: " + productCode + " | Name: " + name + " | Price: " + price;
    }
}
