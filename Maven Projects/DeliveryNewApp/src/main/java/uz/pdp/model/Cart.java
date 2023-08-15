package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    private int id;
    private Food food;
    private int quantity;


    @Override
    public String toString() {
        return "ID: " + this.id + " | Food: " + food.getName() + " | Quantity: " + this.quantity;
    }
}
