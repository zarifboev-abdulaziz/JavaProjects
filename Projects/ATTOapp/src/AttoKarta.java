public class AttoKarta {
    //Fields
   private int id;
   private String name;
   private String cardNumber;
   private double balance;


    public AttoKarta() {
    }

    public AttoKarta(int id, String name, String cardNumber, double balance) {
        this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AttoKarta{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
