package uz.pdp.service.interfaces;

import uz.pdp.model.User;

public interface UserMyCartService {

    void myCartMenu(User user);

    void clothList(User user);

    void checkOut(User user);

    void checkAveragePrice(User user);


}
