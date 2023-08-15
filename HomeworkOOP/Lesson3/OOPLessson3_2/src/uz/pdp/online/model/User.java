package uz.pdp.online.model;

import java.util.Scanner;

public class User {
    Scanner scan = new Scanner(System.in);

    long id;
    public String username;
    public String email;
    private String password;
    public String name;
    String address;



    // Constructors
    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Methods
    public void changePassword(){
        if(this.password == null){
            System.out.println("Avval ro'yhatdan o'ting.");
            return;
        }
        System.out.print("Enter your old password: ");
        String oldPassword = scan.nextLine();
        if(oldPassword.equals(this.password)){
            System.out.print("Enter your new Password: ");
            setPassword(scan.nextLine());
            System.out.println("Successfully changed");
        } else {
            System.out.println("Old Password is wrong");
        }

    }


    //Getters and Setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
