package uz.pdp.servis;

import uz.pdp.model.User;

public interface UserService {

    User login();

    void register();

    void userMenu(User user);

    void sendMoney(User user);

    void showBalance(User user);

    void fillBalance(User user);

    void myCards(User user);


}
