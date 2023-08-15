public interface Service {

    User userLogin();

    void userRegistration();

    void addNews(User user);

    void myNews(User user);

    void showAllNews(User user);

    void showBalance(User user);

    //Admin manager

    Admin adminLogin();

    void setNews(Admin admin);

    void showRejectedNews();

    void showConfirmedNews();

    void showAdminBalance(Admin admin);

    void addAdmin();




}
