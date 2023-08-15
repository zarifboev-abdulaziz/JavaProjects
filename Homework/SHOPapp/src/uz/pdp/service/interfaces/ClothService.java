package uz.pdp.service.interfaces;

import uz.pdp.model.User;

public interface ClothService {

    void clothMenu(User user);

    void showMyClothes(User user);

    void addCloth(User user);

    void updateCloth(User user);

    void deleteCloth(User user);

}
