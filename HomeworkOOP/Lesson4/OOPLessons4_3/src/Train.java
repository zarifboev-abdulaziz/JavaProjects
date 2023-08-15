public class Train extends Transport{
    //Properties
    String clientType;
    String wheelbase;
    int totalCapacity; // seating

    //Constructor
    public Train() {
    }

    public Train(String clientType, String wheelbase, int totalCapacity) {
        this.clientType = clientType;
        this.wheelbase = wheelbase;
        this.totalCapacity = totalCapacity;
    }

    public Train(String brand, String model, String madeInCountry, String color, double price, float speed, String clientType, String wheelbase, int totalCapacity) {
        super(brand, model, madeInCountry, color, price, speed);
        this.clientType = clientType;
        this.wheelbase = wheelbase;
        this.totalCapacity = totalCapacity;
    }

    //Methods


}
