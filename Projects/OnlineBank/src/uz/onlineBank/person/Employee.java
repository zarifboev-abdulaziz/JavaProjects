package uz.onlineBank.person;

import uz.onlineBank.enums.RolePerson;

public class Employee extends Person{
    //Properties


    //Constructors
    public Employee() {
    }

    public Employee(Integer id, String name, String phone, String password, RolePerson role) {
        super(id, name, phone, password, role);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
