package uz.pdp.model;

import uz.pdp.model.abs.User;
import uz.pdp.model.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private boolean isActive = true;
    private List<OrderItem> myCart = new ArrayList<>();

    public Customer() {
    }

    public Customer(String fullName, String username, String password,
                    double balance,  List<OrderItem> myCart) {
        super(fullName, username, password, balance, Role.CUSTOMER);
        this.isActive = isActive;
        this.myCart = myCart;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<OrderItem> getMyCart() {
        return myCart;
    }

    public void setMyCart(List<OrderItem> myCart) {
        this.myCart = myCart;
    }

}
