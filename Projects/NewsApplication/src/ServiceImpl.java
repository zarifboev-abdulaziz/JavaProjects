import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceImpl implements Service{

    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);

    //Listlar
    int userId = 0;
    List<User> userList = new ArrayList<>();

    int newsId = 0;
    List<News> newsList = new ArrayList<>();
    List<News> meningYangiliklarim = new ArrayList<>();

    int adminId = 0;
    List<Admin> adminList = new ArrayList<>();

    public ServiceImpl() {
        Admin admin = new Admin("Abdulaziz", "qwerty", "1234", 10000, adminId++);
        adminList.add(admin);
    }

    @Override
    public void signUp() {
        System.out.println("Ismingizni kiriting: ");
        String fullName = scannerStr.nextLine();

        System.out.println("Emailingizni kiriting: ");
        String email = scannerStr.nextLine();

        System.out.println("Parolni kiriting:");
        String parol = scannerStr.nextLine();

        User user = new User(fullName, email, parol, userId++, 0);
        userList.add(user);
        System.out.println("Akkount yaratildi."); //Successfully
    }

    @Override
    public User signIn() {
        System.out.println("Emailingizni kiriting : ");
        String inputEmail = scannerStr.nextLine();

        System.out.println("Parolingizni kiriting: ");
        String inputParol = scannerStr.nextLine();

        for (User user : userList) {
            if(user.getEmail().equals(inputEmail) && user.getParol().equals(inputParol)){
                return user;
            }
        }
        System.out.println("Ro'yxatdan o'ting!!!");
        //signIn();
        return null;
    }

    @Override
    public void yangilikQushish() {
        System.out.println("Sarlavhasini kiriting: ");
        String inputTitle = scannerStr.nextLine();

        System.out.println("Matnini kiriting: ");
        String inputText = scannerStr.nextLine();

        News news = new News(inputTitle, inputText, false, newsId++);
        newsList.add(news);
        meningYangiliklarim.add(news);

        System.out.println("Yangilik qo'shildi.");

    }

    @Override
    public void meningYangiliklarim() {
        for (News news : meningYangiliklarim) {
            System.out.println(news.toString());
        }
    }

    @Override
    public void adminSignUp() {
        System.out.println("Ismingizni kiriting: ");
        String fullName = scannerStr.nextLine();

        System.out.println("Emailingizni kiriting: ");
        String email = scannerStr.nextLine();

        System.out.println("Parolni kiriting: ");
        String parol = scannerStr.nextLine();

        Admin admin = new Admin(fullName, email,parol, 0, adminId++);
        adminList.add(admin);

    }

    @Override
    public Admin adminSignIn() {
        System.out.println("Emailingizni kiriting: ");
        String inputEmail = scannerStr.nextLine();

        System.out.println("Parolni kiriting: ");
        String inputParol = scannerStr.nextLine();

        for (Admin admin : adminList) {
            if(admin.getEmail().equals(inputEmail) && admin.getParol().equals(inputParol)){
                return admin;
            }
        }

        System.out.println("Admin ro'yxatda yo'q");
        return null;
    }

    @Override
    public void yangilikniTasdiqlash(Admin admin) {
        for (News news : newsList) {
            System.out.println(news.toString());
        }
        
        System.out.println("Yangilikni Id sini kiriting: ");
        int inputId = scannerInt.nextInt();

        for (News news : newsList) {
            if(news.getNewsId() == inputId){
                news.setTasdiqlanganmi(true);
                System.out.println("Yangilik tasdiqlandi");
                news.getAuthor().setBalance(news.getAuthor().getBalance()+100);
                admin.setBalance(admin.getBalance()-100);
                break;
            }
        }

        System.out.println("Wrong ID");
    }

    @Override
    public void showRejectedNews() {
        for (News news : newsList) {
            if(!news.isTasdiqlanganmi()){
                System.out.println(news.toString());
            }
        }
    }

    @Override
    public void showConfirmedNews() {
        for (News news : newsList) {
            if(news.isTasdiqlanganmi()){
                System.out.println(news.toString());
            }
        }
    }
}
