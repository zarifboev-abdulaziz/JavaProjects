package uz.pdp.servis;

import uz.pdp.Sklad;
import uz.pdp.model.TulovTuri;
import uz.pdp.servis.interfaceServis.TulovTuriServis;

import java.util.Scanner;

public class TulovTuriServisImpl implements TulovTuriServis {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    @Override
    public void tulovTuriMenu() {

        System.out.println("1.To'lov Turlari Ro'yxati\n2.To'lov sistemasini qo'shish\n3.To'lov Turini Tahrirlash\n4.To'lov Turini O'chirish\n5.Orqaga");
        int option = scannerInt.nextInt();

        switch (option){
            case 1:
                tulovTurlariRuyxati();
                break;
            case 2:
                tulovSistemasiniQushish();
                break;
            case 3:
                tulovTuriniTahrirlash();
                break;
            case 4:
                tulovTuriniUchirish();
                break;
            case 5: return;
            default:
                System.out.println("Noto'g'ri raqam kiritildi");
        }


    }

    @Override
    public void tulovTurlariRuyxati() {
        for (TulovTuri tulovTuri : Sklad.tulovTuriList) {
            System.out.println(tulovTuri);
        }
    }

    @Override
    public void tulovSistemasiniQushish() {
        System.out.println("Nomini kiriting:");
        String nomi = scannerStr.toString();

        System.out.println("Komissiyasini kiriting: ");
        double komissiya = scannerInt.nextDouble();

        TulovTuri tulovTuri = new TulovTuri((int)(Math.random()*1000), nomi, komissiya);
        Sklad.tulovTuriList.add(tulovTuri);
        System.out.println("Successfully added!!!");
    }

    @Override
    public void tulovTuriniTahrirlash() {
        tulovTurlariRuyxati();
        System.out.println("IDni kiriting: ");
        int id = scannerInt.nextInt();

        for (TulovTuri tulovTuri : Sklad.tulovTuriList) {
            if(tulovTuri.getId() == id){
                System.out.println("Yangi ism kiriting: ");
                String nomi = scannerStr.nextLine();

                System.out.println("Komissiyani kiriting : ");
                double komissiya = scannerInt.nextDouble();

                tulovTuri.setNomi(nomi);
                tulovTuri.setKomissiya(komissiya);
                System.out.println("Successfully updated");
            }
        }

    }

    @Override
    public void tulovTuriniUchirish() {
        tulovTurlariRuyxati();
        System.out.println("IDni kiriting: ");
        int inputId =scannerInt.nextInt();

        for (TulovTuri tulovTuri : Sklad.tulovTuriList) {
            if(tulovTuri.getId() == inputId){
                Sklad.tulovTuriList.remove(tulovTuri);
                System.out.println("Successfully removed!!!");
                break;
            }
        }

    }

}
