public class Intercity extends Train{
    //Properties
    float intercityLength;
    int luxSeatingCapacity;
    int comfortSeatingCapacity;
    int economSeatingCapacity;

    //Constructors
    public Intercity() {
    }

    public Intercity(float intercityLength, int luxSeatingCapacity, int comfortSeatingCapacity, int economSeatingCapacity) {
        this.intercityLength = intercityLength;
        this.luxSeatingCapacity = luxSeatingCapacity;
        this.comfortSeatingCapacity = comfortSeatingCapacity;
        this.economSeatingCapacity = economSeatingCapacity;
    }

    public Intercity(String clientType, String wheelbase, int totalCapacity, float intercityLength, int luxSeatingCapacity, int comfortSeatingCapacity, int economSeatingCapacity) {
        super(clientType, wheelbase, totalCapacity);
        this.intercityLength = intercityLength;
        this.luxSeatingCapacity = luxSeatingCapacity;
        this.comfortSeatingCapacity = comfortSeatingCapacity;
        this.economSeatingCapacity = economSeatingCapacity;
    }

    public Intercity(String brand, String model, String madeInCountry, String color, double price, float speed, String clientType, String wheelbase, int totalCapacity, float intercityLength, int luxSeatingCapacity, int comfortSeatingCapacity, int economSeatingCapacity) {
        super(brand, model, madeInCountry, color, price, speed, clientType, wheelbase, totalCapacity);
        this.intercityLength = intercityLength;
        this.luxSeatingCapacity = luxSeatingCapacity;
        this.comfortSeatingCapacity = comfortSeatingCapacity;
        this.economSeatingCapacity = economSeatingCapacity;
    }

    //Methods


}
