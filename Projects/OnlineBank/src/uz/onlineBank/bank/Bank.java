package uz.onlineBank.bank;

import uz.onlineBank.person.Employee;
import uz.onlineBank.person.User;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    //Properties

    private Integer id;
    private String name;
    private List<User> users = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    //Constructors
    public Bank() {
    }

    public Bank(Integer id, String name, List<User> users, List<Employee> employees, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.employees = employees;
        this.transactions = transactions;
    }

    //Getter and Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                ", employees=" + employees +
                ", transactions=" + transactions +
                '}';
    }
}
