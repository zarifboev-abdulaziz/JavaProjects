package uz.pdp.service.authService;

import uz.pdp.model.User;

public interface AuthService {
    void register();

    User logIn();
}
