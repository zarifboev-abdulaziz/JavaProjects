package uz.pdp.amigoscodejsonwebtoken.service;

import uz.pdp.amigoscodejsonwebtoken.domain.Role;
import uz.pdp.amigoscodejsonwebtoken.domain.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();


}
