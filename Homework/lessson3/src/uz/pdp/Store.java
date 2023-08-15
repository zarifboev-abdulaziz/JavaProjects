package uz.pdp;

import uz.pdp.model.Cloth;
import uz.pdp.model.enums.ClothSize;
import uz.pdp.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Store {

    public static List<Cloth> clothesList = new ArrayList<>(Arrays.asList(
            new Cloth(11, new User(), "Shapka", ClothSize.XL, 15000),
            new Cloth(12, new User(), "Oq ko'ylak" , ClothSize.XXL, 30000),
            new Cloth(13, new User(), "Shim", ClothSize.XXXL, 60000),
            new Cloth(14, new User(), "Tufli", ClothSize.XXL, 10000)
    ));

    public static List<User> userList = new ArrayList<>();




}
