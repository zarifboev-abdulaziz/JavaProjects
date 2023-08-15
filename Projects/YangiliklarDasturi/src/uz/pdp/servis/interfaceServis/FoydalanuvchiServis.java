package uz.pdp.servis.interfaceServis;

import uz.pdp.model.Foydalanuvchi;

public interface FoydalanuvchiServis {

    Foydalanuvchi kirish();

    void ruyxatdanUtish();

    void balansniTekshirish(Foydalanuvchi foydalanuvchi);

    void hammaYangiliklarniKursatish(Foydalanuvchi foydalanuvchi);

    void parolniuzgartirish(Foydalanuvchi foydalanuvchi);
}
