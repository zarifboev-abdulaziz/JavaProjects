import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        ServiceImpl service = new ServiceImpl();


        System.out.println("Welcome to News App");

        boolean active = true;
        while (active){
            System.out.println("1.Enter as User\n2.Enter as Admin\n3.Exit\nChoose one: ");
            int MainOption = scannerInt.nextInt();

            switch (MainOption){
                case 1:{
                    int flag = 0;
                    while (flag == 0){
                        System.out.println("1.Log in\n2.Register\n3.back\nchoose one: ");
                        int userMenu = scannerInt.nextInt();
                        switch (userMenu){
                            case 1:{
                                User user = service.userLogin();
                                if (user != null) {
                                    int userActive = 0;
                                    while (userActive == 0) {
                                        System.out.println("1.Add News\n2.Show my news\n3.Read All News\n4.Show balance\n5.Log out\nchoose one: ");
                                        int userOption = scannerInt.nextInt();
                                        switch (userOption){
                                            case 1:{
                                                service.addNews(user);
                                            }break;
                                            case 2:{
                                                service.myNews(user);
                                            }break;
                                            case 3:{
                                                service.showConfirmedNews();
                                            }break;
                                            case 4:{
                                                service.showBalance(user);
                                            }break;
                                            case 5:{
                                                userActive = -1;
                                            }break;
                                            default:{
                                                System.out.println("Wrong option");
                                            }break;
                                        }

                                    }
                                }

                            }break;
                            case 2:{
                                service.userRegistration();
                            }break;
                            case 3:{
                                flag = -1;
                            }break;
                            default:{
                                System.out.println("Wrong option!!!");
                            }break;
                        }


                    }


                }break;

                case 2:{
                    Admin admin = service.adminLogin();
                    if(admin != null){
                        int adminActive = 0;
                        while (adminActive == 0) {
                            System.out.println("1.Set News\n2.Show rejected News\n3.Show Confirmed News\n4.Show balance\n5.Add admin\n6.Log out\nChoose one");
                            int adminOption = scannerInt.nextInt();
                            switch (adminOption) {
                                case 1: {
                                    service.setNews(admin);
                                }break;
                                case 2: {
                                    service.showRejectedNews();
                                }break;
                                case 3: {
                                    service.showConfirmedNews();
                                }break;
                                case 4: {
                                    service.showAdminBalance(admin);
                                }break;
                                case 5: {
                                    service.addAdmin();
                                }break;
                                case 6: {
                                    adminActive = -1;
                                }break;
                                default:{
                                    System.out.println("Wrong option!!!");
                                }break;

                            }
                        }
                    }

                }break;

                case 3:{
                    active = false;
                }break;
                default:{
                    System.out.println("Wrong option!!!");
                }break;
            }

        }







    }
}
