package uz.pdp.service.clientService;

import uz.pdp.model.User;

public interface ClientService {
    void showClientMenu(User user);

    void myCart(User user);

    void fillBalance(User user);

    void showBalance(User user);

    void purchasedHistory(User user);

    void showOrderStatus(User user);

    void receivedProduct(User user);

    // log out

    void showProducts(User user);

}
