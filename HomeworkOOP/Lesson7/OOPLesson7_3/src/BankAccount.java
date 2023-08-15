public abstract class BankAccount {
    String owner;
    double balance;


    abstract double deposit(double amount);
    abstract double withdraw(double amount);
}
