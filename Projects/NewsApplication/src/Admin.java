public class Admin {
    //Fieldlar
    private String fullName;
    private String email;
    private String parol;
    private double balance;
    private int adminId;

    //Constructor
    public Admin() {
    }

    public Admin(String fullName, String email, String parol, double balance, int adminId) {
        this.fullName = fullName;
        this.email = email;
        this.parol = parol;
        this.balance = balance;
        this.adminId = adminId;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    //to String

    @Override
    public String toString() {
        return "Admin{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", parol='" + parol + '\'' +
                ", balance=" + balance +
                ", adminId=" + adminId +
                '}';
    }
}
