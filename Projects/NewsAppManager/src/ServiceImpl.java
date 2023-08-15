import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceImpl implements Service{
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);


    //Lists
    int newsId = 0;
    List<News> newsList = new ArrayList<>();

    List<News> myNewsList = new ArrayList<>();

    List<News> confirmedNewsList = new ArrayList<>();

    List<News> rejectedNewsList = new ArrayList<>();

    int userId = 0;
    List<User> userList = new ArrayList<>();

    int adminId = 0;
    List<Admin> adminList = new ArrayList<>();

    //Admin created
    public ServiceImpl(){
        Admin admin = new Admin("Abdulaziz", "aziz@gmail.com", "1234", 10000, adminId++);
        adminList.add(admin);
    }








    @Override
    public User userLogin() {
        System.out.println("Enter your email: ");
        String email = scannerStr.nextLine();

        System.out.println("Enter your password: ");
        String password = scannerStr.nextLine();

        for (User user : userList) {
            if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void userRegistration() {

        System.out.print("Enter your full Name: ");
        String fullName = scannerStr.nextLine();

        System.out.print("Enter your email: ");
        String email = scannerStr.nextLine();

        System.out.println("Enter your password: ");
        String password = scannerStr.nextLine();

        User user = new User(fullName, email, password, 0, userId++);
        userList.add(user);

        System.out.println("User successfully created");

    }

    @Override
    public void addNews(User user) {
        System.out.println("Enter title of the news: ");
        String title = scannerStr.nextLine();

        System.out.println("Enter text of the news(1 line): ");
        String text = scannerStr.nextLine();

        News news = new News(title, text, user, newsId++, false);
        newsList.add(news);
        myNewsList.add(news);
        System.out.println("News successfully sent");
    }

    @Override
    public void myNews(User user) {
        for (News news : myNewsList) {
            System.out.println(news.toString());
        }
    }

    @Override
    public void showAllNews(User user) {
        for (News news : confirmedNewsList) {
            System.out.println(news.toString());
        }
    }

    @Override
    public void showBalance(User user) {
        System.out.println(user.getBalance());
    }

    @Override
    public Admin adminLogin() {
        System.out.println("Enter your email: ");
        String email = scannerStr.nextLine();

        System.out.println("Enter your password: ");
        String password = scannerStr.nextLine();

        for (Admin admin : adminList) {
            if(admin.getEmail().equalsIgnoreCase(email) && admin.getPassword().equalsIgnoreCase(password)){
                return admin;
            }
        }
        System.out.println("Admin not found.");
        return null;

    }

    @Override
    public void setNews(Admin admin) {
        for (News news : newsList) {
            System.out.println(news.toString());
        }

        System.out.println("Input news ID in order to confirm: ");
        int inputNewsId = scannerInt.nextInt();

        for (News news : newsList) {
            if(news.getNewsId() == inputNewsId){
                news.setConfirmed(true);
                news.getAuthor().setBalance(news.getAuthor().getBalance() + 100);
                admin.setBalance(admin.getBalance() - 100);
                System.out.println("News successfully confirmed!");
                return;
            }
        }
        System.out.println("News not found");
    }

    @Override
    public void showRejectedNews() {
        for (News news : newsList) {
            if(news.isConfirmed() == false){
                System.out.println(news.toString());
            }
        }
    }

    @Override
    public void showConfirmedNews() {
        for (News news : newsList) {
            if(news.isConfirmed() == true){
                System.out.println(news.toString());
            }
        }
    }

    @Override
    public void showAdminBalance(Admin admin) {
        System.out.println(admin.getBalance());
    }

    @Override
    public void addAdmin() {
        System.out.println("Enter full name: ");
        String fullName = scannerStr.nextLine();

        System.out.println("Enter email: ");
        String email = scannerStr.nextLine();

        System.out.println("Enter password: ");
        String password = scannerStr.nextLine();

        System.out.println("Enter balance: ");
        double balance = new Scanner(System.in).nextDouble();

        Admin admin = new Admin(fullName, email, password, balance, adminId++);
        adminList.add(admin);
        System.out.println("Admin successfully added");
    }


}
