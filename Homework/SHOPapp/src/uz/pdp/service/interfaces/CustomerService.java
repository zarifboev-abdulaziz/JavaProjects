package uz.pdp.service.interfaces;

import uz.pdp.model.User;

public interface CustomerService {

    void customerMenu(User user);

    void buyCloth(User user);

    void myClothes(User user);

    void averagePrice(User user);

    void averagePriceBySize(User user);

    void showBalance(User user);

    void fillBalance(User user);

}
