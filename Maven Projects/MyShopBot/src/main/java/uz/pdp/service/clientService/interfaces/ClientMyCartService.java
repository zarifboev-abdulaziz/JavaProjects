package uz.pdp.service.clientService.interfaces;

import uz.pdp.model.abs.User;

public interface ClientMyCartService {

    void clientMyCartMenu(User customer);

    void showClothesInCart(User customer);

    void addMore(User customer);

    void removeItemFromCart(User customer);

    void clientCheckOut(User customer);



}
