public class Player {
    //Properties
    private int playerId;
    private String fullName;
    private int age;
    private double price;
    private boolean isForSale;
    private String position;
    private String ownedClub;


    //Constructor
    public Player() {
    }

    public Player(int playerId, String fullName, int age, double price, boolean isForSale, String position, String ownedClub) {
        this.playerId = playerId;
        this.fullName = fullName;
        this.age = age;
        this.price = price;
        this.isForSale = isForSale;
        this.position = position;
        this.ownedClub = ownedClub;
    }

    //Getter and Setter


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isForSale() {
        return isForSale;
    }

    public void setForSale(boolean forSale) {
        isForSale = forSale;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOwnedClub() {
        return ownedClub;
    }

    public void setOwnedClub(String ownedClub) {
        this.ownedClub = ownedClub;
    }

    //to String


    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", price=" + price +
                ", isForSale=" + isForSale +
                ", position='" + position + '\'' +
                ", ownedClub='" + ownedClub + '\'' +
                '}';
    }
}
