package uz.pdp.model;

import uz.pdp.model.enums.ClothSize;

public class Cloth {
    private int id;
    private User user;
    private String name;
    private ClothSize size;
    private double price;

    public Cloth() {
    }

    public Cloth(int id, User user, String name, ClothSize size, double price) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClothSize getSize() {
        return size;
    }

    public void setSize(ClothSize size) {
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
        return ", name='" + name +
                ", size=" + size +
                ", price=" + price;
    }
}
