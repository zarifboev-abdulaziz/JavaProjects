import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        ServiceImpl service = new ServiceImpl();


        while (true){
            System.out.println("1.Foydalanuvchi sifatida kirish\n2.Admin sifatida kirish\n3.Dasturdan chiqish");
            int option = scannerInt.nextInt();
            switch (option){
                case 1:{
                    boolean active = true;
                    while (active){
                        System.out.println("1.Kirish\n2.Ro'yxatdan o'tish\n3.Orqaga");
                        int userOption = scannerInt.nextInt();
                        switch (userOption){
                            case 1:{
                               User user = service.signIn();   //Kirish
                                if(user != null){
                                    boolean flag = true;
                                    while (flag){
                                        System.out.println("====UserMenu====\n1.Yangilik qo'shish\n2.Mening yangiliklarim\n3.Hamma yangiliklarni ko'rish\n4.balansni ko'rish\n5.Chiqish");
                                        int clickOption = scannerInt.nextInt();
                                        switch (clickOption){
                                            case 1:{
                                                service.yangilikQushish();
                                            }break;
                                            case 2:{
                                                service.meningYangiliklarim();
                                            }break;
                                            case 3:{
                                                service.showConfirmedNews();
                                            }break;
                                            case 4:{
                                                System.out.println(user.getBalance());
                                            }break;
                                            case 5:{
                                                flag = false;
                                            }break;
                                            default:{
                                                System.out.println("Noto'g'ri raqam kiritildi.");
                                            }break;



                                        }
                                    }




                                }

                            }break;
                            case 2:{
                                service.signUp();  //Ro'yxatdan o'tish
                            }break;
                            case 3:{
                                active = false;
                            }break;
                            default:{
                                System.out.println("Wrong option");
                            }break;
                        }

                    }




                } break;

                case 2: {
                    int flag = 0;
                    while (flag == 0){
                        System.out.println("1.Kirish\n2.Admin qo'shish\n3.Orqaga");
                        int adminOption = scannerInt.nextInt();
                        switch (adminOption){
                            case 1:{
                                Admin admin = service.adminSignIn();
                                if(admin != null){

                                    boolean exit = true;
                                    while(exit){
                                        System.out.println("====AdminMenu====\n1.Yangiliklarni tasdiqlash\n2.Rad etilgan yangiliklar\n3.Tasdiqlangan yangiliklar\n4.Balansni ko'rish\n5.Chiqish");
                                        int adminMenuOption = scannerInt.nextInt();
                                        switch (adminMenuOption){
                                            case 1:{
                                                service.yangilikniTasdiqlash(admin);
                                            }break;
                                            case 2:{
                                                service.showRejectedNews();
                                            }break;
                                            case 3:{
                                                service.showConfirmedNews();
                                            }break;
                                            case 4:{
                                                System.out.println(admin.getBalance());
                                            }break;
                                            case 5:{
                                                exit = false;
                                            }break;
                                            default:{
                                                System.out.println("Noto'g'ri raqam kiritildi");
                                            } break;
                                        }
                                    }
                                }
                            }break;
                            case 2:{
                                service.adminSignUp(); //Admin qo'shish
                            }break;
                            case 3:{
                                flag = -1;
                            }break;
                            default:{
                                System.out.println("Noto'g'ri raqam kiritildi");
                            }break;
                        }



                    }

                }break;
                case 3: return;

                    default:
                    System.out.println("Wrong option");
                    break;
            }
        }






    }
}
