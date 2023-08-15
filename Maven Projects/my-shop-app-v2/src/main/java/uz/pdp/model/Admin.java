package uz.pdp.model;

import uz.pdp.model.abs.User;
import uz.pdp.model.enums.Role;

public class Admin extends User {

    public Admin() {
    }

    public Admin(String fullName, String username, String password, double balance) {
        super(fullName, username, password, balance, Role.ADMIN);
    }

}
