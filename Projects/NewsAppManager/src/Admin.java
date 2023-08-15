public class Admin {
    //Properties
    private String fullName;
    private String email;
    private String password;
    private double balance;
    private int AdminId;

    //Constructors
    public Admin() {
    }

    public Admin(String fullName, String email, String password, double balance, int adminId) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        AdminId = adminId;
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

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int adminId) {
        AdminId = adminId;
    }

    //to String

    @Override
    public String toString() {
        return "Admin{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", AdminId=" + AdminId +
                '}';
    }
}
