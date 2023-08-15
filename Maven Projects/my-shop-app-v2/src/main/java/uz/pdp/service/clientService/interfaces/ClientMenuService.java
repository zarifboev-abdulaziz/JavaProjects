package uz.pdp.service.clientService.interfaces;

import uz.pdp.model.Customer;
import uz.pdp.model.abs.User;

// "3=> Login, 4=> Register," : "5=> Logout, 6=>Show Order history";
//print(BLUE, "1=> See clothes, 2=> My cart, " + isAuthorized + "  0=> Exit");

public interface ClientMenuService {

    void clientMenu(Customer customer);

    void seeClothes(Customer customer);

    void myCart(Customer customer);

    void filterClothes(Customer customer);

    void clientOrderHistory(Customer customer);

    void fillBalance(Customer customer);

    void showBalance(Customer customer);

}
