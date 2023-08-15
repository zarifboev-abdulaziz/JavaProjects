import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        UserServiceImpl service = new UserServiceImpl();

    while (true) {
        System.out.println("1.Log in\n2.Register\n3.Exit\nTanlang: ");
        int option = scannerInt.nextInt();
        switch (option) {
            case 1: {
                User user = service.login();
                if (user != null) {

                    boolean active = true;
                    while (active){
                        System.out.println("1.Hisobni to'ldirish\n2.Karta qo'shish\n3.Kartani o'chirish\n4.Tariflar\n5.Balansni ko'rish\n6.Profilim\n7.Orqaga\nTanlang: ");
                        int menuOption = scannerInt.nextInt();
                        switch (menuOption){
                            case 1:{
                                System.out.println("====Hisobni to'ldirish====");
                            }break;
                            case 2:{
                                System.out.println("====Karta qo'shish ====");
                                service.kartaQoshish(user);
                            }break;
                            case 3:{
                                System.out.println("====Karta o'chirish ====");
                                service.kartaniOchirish(user);
                            }break;
                            case 4:{
                                System.out.println("====Tariflar====");
                            }break;
                            case 5:{
                                System.out.println("====Balansni ko'rish====");
                                service.balansniKorish(user);
                            }break;
                            case 6:{
                                System.out.println("====Profilim====");
                                service.profilim(user);
                            }break;
                            case 7:{
                                active = false;
                            }break;
                            default:{
                                System.out.println("wrong option!!!");
                            }break;
                        }


                    }
                }
            }
            break;
            case 2: {
                service.register();
            }
            break;
            case 3:
                return;
            default:
                System.out.println("Wrong Option");
                break;
        }
    }


    }
}
