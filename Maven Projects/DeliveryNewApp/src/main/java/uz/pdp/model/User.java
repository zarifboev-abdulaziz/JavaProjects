package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.enums.Role;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private double balance;
    private List<Cart> myCart;
    private Role role;

    // for admin, supplier, chief;
    public User(int id, String fullName, String email, String password, double balance, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }


    @Override
    public String toString() {
        return "ID" + id + " | Full name: " + fullName + " | EMAIL: " + email + " | Role: " + role;
    }
}
