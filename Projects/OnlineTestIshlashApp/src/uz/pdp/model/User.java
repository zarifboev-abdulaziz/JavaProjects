package uz.pdp.model;

import uz.pdp.model.enums.Role;

public class User {
    //Fields
    private int id;
    private String fullName;
    private String userName;
    private String password;
    private Role role;
    private double balance;

    public User() {
    }

    public User(int id, String fullName, String userName, String password, Role role, double balance) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
