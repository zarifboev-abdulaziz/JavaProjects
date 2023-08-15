package uz.pdp.servis;

import uz.pdp.Sklad;
import uz.pdp.model.Foydalanuvchi;
import uz.pdp.model.Yangiliklar;
import uz.pdp.model.enums.Role;
import uz.pdp.model.enums.Status;
import uz.pdp.servis.interfaceServis.FoydalanuvchiServis;
import sun.applet.Main;

import java.util.Scanner;

public class FoydalanuvchiServisImpl implements FoydalanuvchiServis {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    @Override
    public Foydalanuvchi kirish() {
        System.out.println("Emailingizni kiriting: ");
        String email = scannerStr.nextLine();

        System.out.println("Parolingizni kiriting: ");
        String parol = scannerStr.nextLine();

        for (Foydalanuvchi foydalanuvchi : Sklad.foydalanuvchiList) {
            if(foydalanuvchi.getEmail().equals(email) && foydalanuvchi.getParol().equals(parol)){
                return foydalanuvchi;
            }
        }
        return null;
    }

    @Override
    public void ruyxatdanUtish() {

        int id = (int)(Math.random()*1000);

        System.out.println("Ismingizni kiriting:");
        String ism = scannerStr.nextLine();

        System.out.println("Emailingizni Kiritng: ");
        String email = scannerStr.nextLine();

        System.out.println("Parolni kiriting: ");
        String parol = scannerStr.nextLine();

        Foydalanuvchi foydalanuvchi = new Foydalanuvchi(id, ism, email, parol, Role.JOURNALIST, 0);
        Sklad.foydalanuvchiList.add(foydalanuvchi);
    }

    @Override
    public void balansniTekshirish(Foydalanuvchi foydalanuvchi) {
        System.out.println("Balans: " + foydalanuvchi.getBalance());
    }

    @Override
    public void hammaYangiliklarniKursatish(Foydalanuvchi foydalanuvchi) {

        switch (foydalanuvchi.getRole()){
            case ADMIN:
                for (Yangiliklar yangiliklar : Sklad.yangiliklarList) {
                    System.out.println(yangiliklar.toString());
                }
                break;

            case JOURNALIST:
                for (Yangiliklar yangiliklar : Sklad.yangiliklarList) {
                    if(yangiliklar.getStatus().equals(Status.TASDIQLANGAN)){
                        System.out.println(yangiliklar);
                    }
                }

        }


    }

    @Override
    public void parolniuzgartirish(Foydalanuvchi foydalanuvchi) {
        int urinish = 0;

        while (true) {
            if(urinish == 3){
                System.out.println("Akkountingiz bloklandi: ");
                return;
            }

            System.out.println("Eski parolni kiriting (0=> orqaga): ");
            String eskiParol = scannerStr.nextLine();
            if(eskiParol.equals("0")){
                return;
            }

            if (foydalanuvchi.getParol().equals(eskiParol)) {
                System.out.println("Yangi parolni kiriting: ");
                String yangiParol = scannerStr.nextLine();

                System.out.println("Yangi parolni tasdiqlang: ");
                String tasdiqParol = scannerStr.nextLine();

                if (yangiParol.equals(tasdiqParol)) {
                    foydalanuvchi.setParol(tasdiqParol);
                    System.out.println("Parol Yangilandi");
                }
            } else{
                System.out.println("Xato parol kiritdingiz.");
                urinish++;
            }
        }

    }
}
