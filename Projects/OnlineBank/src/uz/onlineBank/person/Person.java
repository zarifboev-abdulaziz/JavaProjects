package uz.onlineBank.person;

import uz.onlineBank.enums.RolePerson;

public abstract class Person {

    protected Integer id;
    protected String name;
    protected String phone;
    protected String password;
    protected RolePerson role;

    //Constructor
    public Person() {
    }

    public Person(Integer id, String name, String phone, String password, RolePerson role) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.role = role;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolePerson getRole() {
        return role;
    }

    public void setRole(RolePerson role) {
        this.role = role;
    }

    //to String

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

}
