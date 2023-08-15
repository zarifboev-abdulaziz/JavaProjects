package uz.pdp.servis;

import sun.security.krb5.internal.KdcErrException;
import uz.pdp.Sklad;
import uz.pdp.model.Foydalanuvchi;
import uz.pdp.model.Yangiliklar;
import uz.pdp.model.enums.Status;
import uz.pdp.servis.interfaceServis.JurnalistServis;

import java.util.Scanner;

public class JurnalistServisImpl implements JurnalistServis {
Scanner scannerStr = new Scanner(System.in);
Scanner scannerInt = new Scanner(System.in);
FoydalanuvchiServisImpl foydalanuvchiServis = new FoydalanuvchiServisImpl();

    @Override
    public void jurnalistMenu(Foydalanuvchi jurnalist) {

        System.out.println("1.Yangilik Qo'shish\n2.Mening Yangiliklarim\n3.Balansni tekshirish\n4.Hamma yangiliklarni ko'rish\n5.Parolni O'zagrtirish\n6.Chiqish");
        int jurnalistOption = scannerInt.nextInt();
        switch (jurnalistOption){
            case 1:
                yangilikQushish(jurnalist);
                break;
            case 2:
                meningYangiliklarim(jurnalist);
                break;
            case 3:
                foydalanuvchiServis.balansniTekshirish(jurnalist);
                break;
            case 4:
                foydalanuvchiServis.hammaYangiliklarniKursatish(jurnalist);
                break;
            case 5:
                foydalanuvchiServis.parolniuzgartirish(jurnalist);
                break;
            case 6: return;
            default:
                System.out.println("Noto'g'ri Raqam");
                break;
        }

    }

    @Override
    public void yangilikQushish(Foydalanuvchi journalist) {
        System.out.println("Sarlavhani kiriting: ");
        String sarlavha = scannerStr.nextLine();

        System.out.println("Matnini kiriting: ");
        String matn = scannerStr.nextLine();

        Yangiliklar yangiliklar = new Yangiliklar((int)(Math.random()*1000), sarlavha, matn, journalist, Status.YANGI);

    }

    @Override
    public void meningYangiliklarim(Foydalanuvchi jurnalist) {
        for (Yangiliklar yangiliklar : Sklad.yangiliklarList) {
            if(yangiliklar.getAftor().getIsmi().equals(jurnalist.getIsmi())){
                System.out.println(yangiliklar);
            }
        }
    }

}
