package uz.pdp.model;

import uz.pdp.model.enums.Size;
import uz.pdp.model.enums.Status;

public class Cloth{
    //Properties
    private int id;
    private String name;
    private int ownerId;
    private String color;
    private Size size;
    private double price;
    private Status status;

    public Cloth() {
    }

    public Cloth(int id, String name, int ownerId, String color, Size size, double price, Status status) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.color = color;
        this.size = size;
        this.price = price;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cloth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
