package uz.pdp.servis;

import uz.pdp.Sklad;
import uz.pdp.model.Foydalanuvchi;
import uz.pdp.model.Transaksiya;
import uz.pdp.model.Yangiliklar;
import uz.pdp.model.enums.Role;
import uz.pdp.model.enums.Status;
import uz.pdp.servis.interfaceServis.AdminServis;

import java.util.Scanner;

public class AdminServisImpl implements AdminServis {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    FoydalanuvchiServisImpl foydalanuvchiServis = new FoydalanuvchiServisImpl();
    TulovTuriServisImpl tulovTuriServis = new TulovTuriServisImpl();


    @Override
    public void adminMenu(Foydalanuvchi admin) {

        while (true) {
            System.out.println("1.Jurnalistlar ro'yxatini ko'rish\n2.Yangiliklarni Tasdiqlash\n3.Hisobni To'ldirish\n4.To'lov Turi Menyusi\n5.Transaksiyalar Tarixi\n6.Parolni O'zgartirish\n7.Hamma Yangiliklarni Ko'rish\n8.Chiqish");
            int adminOption = scannerInt.nextInt();

            switch (adminOption) {
                case 1:
                    jurnalistlarRuyxatiniKurish();
                    break;
                case 2:
                    yangiliklarniTasdiqlash(admin);
                    break;
                case 3:
                    hisobniTuldirish(admin);
                    break;
                case 4:
                   tulovTuriServis.tulovTuriMenu();
                    break;
                case 5:
                    transaksiyalarTarixi();
                    break;
                case 6:
                    foydalanuvchiServis.parolniuzgartirish(admin);
                    break;
                case 7:
                    foydalanuvchiServis.hammaYangiliklarniKursatish(admin);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Noto'g'ri Raqam kiritildi");
                    break;
            }
        }
    }

    @Override
    public void jurnalistlarRuyxatiniKurish() {
        for (Foydalanuvchi foydalanuvchi : Sklad.foydalanuvchiList) {
            if(foydalanuvchi.getRole().equals(Role.JOURNALIST)){
                System.out.println(foydalanuvchi.toString());
            }
        }
    }

    @Override
    public void yangiliklarniTasdiqlash(Foydalanuvchi admin) {

        for (Yangiliklar yangiliklar : Sklad.yangiliklarList) {
            if(yangiliklar.getStatus().equals(Status.YANGI)){
                System.out.println(yangiliklar.toString());
            }
        }

        System.out.println("Yangilikning Id sini kiriting: ");
        int id = scannerInt.nextInt();
        Yangiliklar tanlanganYangilik = null;
        for (Yangiliklar yangiliklar : Sklad.yangiliklarList) {
            if(yangiliklar.getId() == id){
                tanlanganYangilik = yangiliklar;
                break;
            }
        }


        System.out.println("1=> Tasdiqlash  2=> Rad etish");
        int option = scannerInt.nextInt();

        switch (option){
            case 1:
                tanlanganYangilik.setStatus(Status.TASDIQLANGAN);
                System.out.println("To'lov Summasini kiriting: ");
                double summa = scannerInt.nextDouble();
                if(summa > admin.getBalance()){
                    System.out.println("Hisobda yetarli mablag' yo'q");
                    return;
                }

                admin.setBalance(admin.getBalance() - summa);
                tanlanganYangilik.getAftor().setBalance(tanlanganYangilik.getAftor().getBalance() + summa);
                System.out.println("Sotib olindi");




                break;
            case 2:
                tanlanganYangilik.setStatus(Status.TASDIQLANMAGAN);
                break;
            default:
                System.out.println("Noto'g'ri raqam kiritildi.");
                break;
        }

        System.out.println("Tekshirildi!!!");
    }

    @Override
    public void hisobniTuldirish(Foydalanuvchi admin) {
        System.out.println("Summani kiriting: ");
        double summa = scannerInt.nextDouble();

        admin.setBalance(admin.getBalance() + summa);
    }


    @Override
    public void transaksiyalarTarixi() {
        for (Transaksiya transaksiya : Sklad.transaksiyaList) {
            System.out.println(transaksiya.toString());
        }
    }
}
