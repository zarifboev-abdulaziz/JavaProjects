package uz.pdp.model;

import uz.pdp.model.abs.AbsClothQuantity;

public class OrderItem extends AbsClothQuantity {

    public OrderItem() {
    }
    public OrderItem(Cloth cloth, int quantity) {
        super(cloth, quantity);
    }

}
