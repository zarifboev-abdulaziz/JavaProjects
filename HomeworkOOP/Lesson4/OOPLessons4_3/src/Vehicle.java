public class Vehicle extends Transport{
    //Properties
    String fuelType;
    int seatingCapacity;         //how many people in the vehicle
    float fuelCapacity;
    float fuelConsumption;
    boolean autoPilot;

    //Constructor
    public Vehicle() {
    }

    public Vehicle(String fuelType, int seatingCapacity, float fuelCapacity, float fuelConsumption) {
        this.fuelType = fuelType;
        this.seatingCapacity = seatingCapacity;
        this.fuelCapacity = fuelCapacity;
        this.fuelConsumption = fuelConsumption;
    }

    public Vehicle(String brand, String model, String madeInCountry, String color, double price, float speed, String fuelType, int seatingCapacity, float fuelCapacity, float fuelConsumption) {
        super(brand, model, madeInCountry, color, price, speed);
        this.fuelType = fuelType;
        this.seatingCapacity = seatingCapacity;
        this.fuelCapacity = fuelCapacity;
        this.fuelConsumption = fuelConsumption;
    }

    //Method
    public void autopilotOn(){
    this.autoPilot = true;
    }


}
