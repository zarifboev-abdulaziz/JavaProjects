package uz.pdp.service.interfaces;

import uz.pdp.model.abs.User;

public interface AuthService {

    User login();

    void register();

    void logout(User authorizedUser);
}
