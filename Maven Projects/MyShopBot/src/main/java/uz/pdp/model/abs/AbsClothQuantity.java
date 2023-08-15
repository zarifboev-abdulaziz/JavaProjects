package uz.pdp.model.abs;

import uz.pdp.model.Cloth;

public abstract class AbsClothQuantity extends AbsEntity{
    private Cloth cloth;
    private int quantity;

    public AbsClothQuantity() {
    }

    public AbsClothQuantity(Cloth cloth, int quantity) {
        this.cloth = cloth;
        this.quantity = quantity;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cloth: " + cloth.getName() +
                ", Quantity: " + quantity;
    }
}
