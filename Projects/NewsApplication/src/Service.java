public interface Service {

    void signUp();   //Ro'yxatdan o'tish

    User signIn();  //Kirish

    void yangilikQushish();

    void meningYangiliklarim();

    void adminSignUp(); // Admin qo'shish

    Admin adminSignIn(); //Admin kirish

    void yangilikniTasdiqlash(Admin admin);

    void showRejectedNews(); //Tasdiqlanmagan yangiliklarni ko'rish

    void showConfirmedNews(); //Tasdiqlangan yangiliklarni ko'rish


}
