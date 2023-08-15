package uz.pdp;


import uz.pdp.model.Foydalanuvchi;
import uz.pdp.model.Transaksiya;
import uz.pdp.model.TulovTuri;
import uz.pdp.model.Yangiliklar;
import uz.pdp.model.enums.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sklad {

    public static List<Foydalanuvchi> foydalanuvchiList = new ArrayList<>(Arrays.asList(
            new Foydalanuvchi(10, "Abdulaziz", "Abdulaziz", "12345", Role.ADMIN, 10000),
            new Foydalanuvchi(11, "Javohir", "user", "1234", Role.JOURNALIST, 0)
    ));

    public static List<Yangiliklar> yangiliklarList = new ArrayList();

    public static List<Transaksiya> transaksiyaList = new ArrayList<>();

    public static List<TulovTuri> tulovTuriList = new ArrayList<>();


}
