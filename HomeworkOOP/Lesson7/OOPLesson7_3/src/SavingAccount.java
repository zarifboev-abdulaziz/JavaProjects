public class SavingAccount extends BankAccount{
    String owner;
    double balance;

    //methods
    @Override
    double deposit(double amount) {
        if(amount > 0 && amount >= this.balance){
            return this.balance - amount;
        }
        return 0;
    }

    @Override
    double withdraw(double amount) {
        if(amount > 0){
            return this.balance + amount;
        }
        return 0;
    }

    public void save(){
        System.out.println(this.balance);
    }
}
