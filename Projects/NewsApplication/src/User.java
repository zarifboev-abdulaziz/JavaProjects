import java.util.List;

public class User {
    //Fieldlar
    private String fullName;
    private String email;
    private String parol;
    private int userId;
    private double balance;
    private List<News> meningYangiliklarim;

    //Constructors
    public User() {
    }

    public User(String fullName, String email, String parol, int userId, double balance) {
        this.fullName = fullName;
        this.email = email;
        this.parol = parol;
        this.userId = userId;
        this.balance = balance;
    }

    //Getter and setter

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

    public String getParol() {
        return parol;
    }

    public void setParol(String parol) {
        this.parol = parol;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //to String

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", parol='" + parol + '\'' +
                ", userId=" + userId +
                ", balance=" + balance +
                '}';
    }
}
