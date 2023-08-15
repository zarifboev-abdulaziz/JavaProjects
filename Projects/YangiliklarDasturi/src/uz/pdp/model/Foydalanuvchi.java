package uz.pdp.model;

import uz.pdp.model.enums.Role;

public class Foydalanuvchi {//User
   private int id;
   private String ismi;
   private String email;
   private String parol;
   private Role role;
   private double balance;

    public Foydalanuvchi() {
    }

    public Foydalanuvchi(int id, String ismi, String email, String parol, Role role, double balance) {
        this.id = id;
        this.ismi = ismi;
        this.email = email;
        this.parol = parol;
        this.role = role;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsmi() {
        return ismi;
    }

    public void setIsmi(String ismi) {
        this.ismi = ismi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParol() {
        return parol;
    }

    public void setParol(String parol) {
        this.parol = parol;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Foydalanuvchi{" +
                "id=" + id +
                ", ismi='" + ismi + '\'' +
                ", email='" + email + '\'' +
                ", parol='" + parol + '\'' +
                ", role=" + role +
                ", balance=" + balance +
                '}';
    }
}
