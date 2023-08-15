package uz.pdp.service.interfaceServis;

import uz.pdp.model.User;

public interface UserService {

    User login();

    void register();

    void checkBalance(User user);
}
