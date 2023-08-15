package uz.pdp.service.cheefService;

import uz.pdp.model.User;

public interface CheefServiceInterface {
    void showCheefMenu(User cheef);
    void showUnreadyFastFoods(User cheef);
    void readyFastFoods(User cheef);


}
