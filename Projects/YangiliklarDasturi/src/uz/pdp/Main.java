package uz.pdp;

import uz.pdp.model.Foydalanuvchi;
import uz.pdp.servis.AdminServisImpl;
import uz.pdp.servis.FoydalanuvchiServisImpl;
import uz.pdp.servis.JurnalistServisImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        FoydalanuvchiServisImpl foydalanuvchiServis = new FoydalanuvchiServisImpl();
        AdminServisImpl adminServis = new AdminServisImpl();
        JurnalistServisImpl jurnalistServis = new JurnalistServisImpl();




        while (true){
            System.out.println("1.Kirish\n2.Ro'yxatdan O'tish\n3.Chiqish");
            int option = scannerInt.nextInt();

            switch (option){
                case 1:
                    Foydalanuvchi foydalanuvchi = foydalanuvchiServis.kirish();
                    if(foydalanuvchi != null){

                        switch (foydalanuvchi.getRole()){
                            case ADMIN:
                                adminServis.adminMenu(foydalanuvchi);
                                break;

                            case JOURNALIST:
                                jurnalistServis.jurnalistMenu(foydalanuvchi);
                                break;
                        }


                    }else{
                        System.out.println("Foydalanuvchi topilmadi.");
                    }


                    break;
                case 2:
                    foydalanuvchiServis.ruyxatdanUtish();
                    break;
                case 3: return;

                default:
                    System.out.println("Noto'g'ri raqam kiritildi");
                    break;
            }
        }





    }
}
