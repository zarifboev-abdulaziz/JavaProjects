import sun.plugin2.message.CustomSecurityManagerAckMessage;

import java.util.*;

public class UserServiceImpl implements UserService{
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);


    List<User> userList = new ArrayList<>(Arrays.asList(
            new User(1, "Abdulaziz", Status.TALABA, "azizgmail.com", "123")
    ));
    List<AttoKarta> attoKartaList = new ArrayList<>();
    List<AttoKarta> meningKartalarim = new ArrayList<>();

    @Override
    public User login(){
        System.out.println("Emailingizni kiritng: ");
        String email = scannerStr.nextLine();

        System.out.println("Parolingizni kiriting: ");
        String parol = scannerStr.nextLine();

        for (User user : userList) {
            if(user.getEmail().equalsIgnoreCase(email) && user.getParol().equalsIgnoreCase(parol)){
                return user;
            }
        }
        System.out.println("User not found");
        return null;
    }

    @Override
    public void register() {
        System.out.println("Ismingizni kiriting: ");
        String fullName = scannerStr.nextLine();

        System.out.println("Emailingizni kiriting: ");
        String email = scannerStr.nextLine();

        System.out.println("Parolni kiriting: ");
        String parol = scannerStr.nextLine();

        System.out.println("Statusingizni tanlang:\n1.Talaba\n2.O'quvchi\n3.Aholi\n4.Pensioner\nBittasini tanlang: ");
        int option = scannerInt.nextInt();
        Status status = null;
        switch (option){
            case 1:
                status = Status.TALABA;
                break;
            case 2:
                status = Status.OQUVCHI;
                break;
            case 3:
                status = Status.AHOLI;
                break;
            case 4:
                status = Status.PENSIONER;
                break;
            default:
                System.out.println("Noto'g'ri tanladingiz!!!");
                break;
        }
        User user = new User((int)(Math.random()*1000), fullName, status, email, parol);
        userList.add(user);
        System.out.println("Akkount yaratildi!!!");
    }

    @Override
    public void hisobniToldirish(User user) {
        System.out.println("Kartani tanlang ");
        for (AttoKarta attoKarta : meningKartalarim) {
            System.out.println(attoKarta.toString());
        }

        System.out.println("Id sini kiriting: ");
        int inputId = scannerInt.nextInt();

        boolean isFound = false;
        for (AttoKarta attoKarta : meningKartalarim) {
            if(attoKarta.getId() == inputId){
                System.out.println("Summani kiriting: ");
                double summa = scannerInt.nextDouble();
                isFound = true;
                attoKarta.setBalance(attoKarta.getBalance() + summa);
                break;
            }
        }

        if(isFound == true){
            System.out.println("Pul o'tkazildi");
        } else {
            System.out.println("Karta topilmadi!!!");
        }
    }

    @Override
    public void kartaQoshish(User user) {
        System.out.println("Kartani nomini kiriting: ");
        String name = scannerStr.nextLine();

        System.out.println("Karta raqamini kiriting: ");
        String cardNumber = scannerStr.nextLine();

        AttoKarta attoKarta = new AttoKarta((int)(Math.random()*1000), name, cardNumber, 0);
        attoKartaList.add(attoKarta);
        meningKartalarim.add(attoKarta);

        System.out.println("Karta qo'shildi!!!");

    }

    @Override
    public void kartaniOchirish(User user) {
        for (AttoKarta attoKarta : meningKartalarim) {
            System.out.println(attoKarta.toString());
        }

        System.out.println("Idni kiriting: ");
        int inputId = scannerInt.nextInt();

        boolean topildi = false;
        for (AttoKarta attoKarta : meningKartalarim) {
            if(attoKarta.getId() == inputId){
                meningKartalarim.remove(attoKarta);
                attoKartaList.remove(attoKarta);
                System.out.println("Karta o'chirildi.");
                topildi = true;
                break;
            }
        }
        if(topildi == false){
            System.out.println("ID topilmadi: ");
        }
    }

    @Override
    public void tariflar(User user) {
       switch (user.getRole()){
           case OQUVCHI:{
               System.out.println("Sotib olish uchun tanlang: \n1.O'M - (Avtobus, metro), Narxi - 83000\n2.O' - (Avtobus) - 63000\nTanlang: ");
               int option = scannerInt.nextInt();
               switch (option){
                   case 1:{
                       for (AttoKarta attoKarta : meningKartalarim) {
                           System.out.println(attoKarta.toString());
                       }
                       System.out.println("Sotib olish uchun kartani IDsini kiriting: ");
                       int inputId = scannerInt.nextInt();
                       for (AttoKarta attoKarta : meningKartalarim) {
                           if(attoKarta.getId() == inputId){
                               attoKarta.setBalance(attoKarta.getBalance() - 83000);
                           }
                       }
                       System.out.println("Tarif sotib olindi");
                   }break;
                   case 2:{
                       for (AttoKarta attoKarta : meningKartalarim) {
                           System.out.println(attoKarta.toString());
                       }
                       System.out.println("Sotib olish uchun kartani IDsini kiriting: ");
                       int inputId = scannerInt.nextInt();
                       for (AttoKarta attoKarta : meningKartalarim) {
                           if(attoKarta.getId() == inputId){
                               attoKarta.setBalance(attoKarta.getBalance() - 63000);
                           }
                       }
                       System.out.println("Tarif sotib olindi");
                   }break;
                   default:{
                       System.out.println("Wrong option!!");
                   }break;
               }

           }break;

           case TALABA:{}break;
           case AHOLI:{}break;
           case PENSIONER:{}break;
       }



    }

    @Override
    public void balansniKorish(User user) {
        for (AttoKarta attoKarta : meningKartalarim) {
            System.out.println("Karta nomi: " + attoKarta.getName() + ", Karta raqami: " + attoKarta.getCardNumber() + ", Balansi: " + attoKarta.getBalance());
        }

    }

    @Override
    public void profilim(User user) {
        System.out.println(user.toString());
    }
}
