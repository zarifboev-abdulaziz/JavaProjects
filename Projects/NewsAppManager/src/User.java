import java.util.List;

public class User {
    //Properties
    private String fullName;
    private String email;
    private String password;
    private List<News> myNewsList;
    private double balance;
    private int userId;

    //Constructors
    public User() {

    }

    public User(String fullName, String email, String password, double balance, int userId) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.userId = userId;
    }

    //Getter and Setter

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //to String
    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }
}
