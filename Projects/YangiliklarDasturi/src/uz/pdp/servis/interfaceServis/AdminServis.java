package uz.pdp.servis.interfaceServis;

import uz.pdp.model.Foydalanuvchi;

public interface AdminServis {

    void adminMenu(Foydalanuvchi admin);

    void jurnalistlarRuyxatiniKurish();

    void yangiliklarniTasdiqlash(Foydalanuvchi admin);

    void hisobniTuldirish(Foydalanuvchi admin);

    void transaksiyalarTarixi();

}
