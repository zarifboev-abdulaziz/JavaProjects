package uz.pdp.model.abs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.OrderItem;
import uz.pdp.model.enums.Role;
import uz.pdp.model.enums.Stage;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class User extends AbsEntity {
    private String fullName;
    private String username;
    private String password;
    private String phoneNumber;
    private double balance;
    private Role role = Role.CUSTOMER;
    private boolean isActive = true;
    private List<OrderItem> myCart = new ArrayList<>();
    private String chatId;
    private Stage stage;
    private int clothPage = 0;
    private Integer currentMessageId = null;

//Admin
    public User(String fullName,
                String username, String password, double balance, Role role) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }

    public User(String fullName, String username, String password, double balance, Role role, boolean isActive, List<OrderItem> myCart) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.role = role;
        this.isActive = isActive;
        this.myCart = myCart;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", role=" + role +
                '}';
    }
}
