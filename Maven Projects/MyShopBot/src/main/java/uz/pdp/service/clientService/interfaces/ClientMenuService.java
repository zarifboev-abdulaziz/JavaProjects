package uz.pdp.service.clientService.interfaces;

// "3=> Login, 4=> Register," : "5=> Logout, 6=>Show Order history";
//print(BLUE, "1=> See clothes, 2=> My cart, " + isAuthorized + "  0=> Exit");

import uz.pdp.model.abs.User;

public interface ClientMenuService {

    void clientMenu(User customer);

    void seeClothes(User customer);

    void myCart(User customer);

    void filterClothes(User customer);

    void clientOrderHistory(User customer);

    void fillBalance(User customer);

    void showBalance(User customer);

}
