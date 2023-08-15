package uz.pdp.service.clientService.interfaces;

import uz.pdp.model.Customer;

public interface ClientMyCartService {

    void clientMyCartMenu(Customer customer);

    void showClothesInCart(Customer customer);

    void addMore(Customer customer);

    void removeItemFromCart(Customer customer);

    void clientCheckOut(Customer customer);



}
