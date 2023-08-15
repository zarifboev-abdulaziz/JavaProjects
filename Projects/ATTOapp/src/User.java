public class User {
    //Fields
    int id;
    private String fullName;
    private Status role;
    private String email;
    private String parol;

    public User() {
    }

    public User(int id, String fullName, Status role, String email, String parol) {
        this.id = id;
        this.fullName = fullName;
        this.role = role;
        this.email = email;
        this.parol = parol;
    }

    //Getter and Setter

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Status getRole() {
        return role;
    }

    public void setRole(Status role) {
        this.role = role;
    }

    //to String

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                '}';
    }
}
