package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.abs.AbsEntity;
import uz.pdp.model.enums.Size;
import uz.pdp.utils.Util;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cloth extends AbsEntity implements Comparable<Cloth> {
    private String name;
    private int productCode = (int) (Math.random() * (9999 - 1000)) + 1000;
    private Color color;
    private Size size;
    private double price;
    private double discount;
    private String imgUrl;


    public Cloth(String name, Color color, Size size, double price, double discount) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String toString() {
        String discountValue = "";

        if (discount > 0){
            discountValue = ", " + Util.printInRed("Discount: " + discount + "%");
        }else {
            discountValue = "";
        }


        return "Name: " + name +
                ", Product Code: " + productCode +
                ", Color: " + color.getName() +
                ", Size: " + size +
                ", Price: " + price + discountValue;
    }


    public int compareTo(Cloth o) {
        return 0;
    }
}
