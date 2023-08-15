public class Truck extends Vehicle{
    //Properties
    float cargoVolume;
    float maxWeight;
    float height;
    String location;

    //Constructors

    public Truck() {
    }

    public Truck(float cargoVolume, float maxWeight, float height, String location) {
        this.cargoVolume = cargoVolume;
        this.maxWeight = maxWeight;
        this.height = height;
        this.location = location;
    }

    public Truck(String fuelType, int seatingCapacity, float fuelCapacity, float fuelConsumption, float cargoVolume, float maxWeight, float height, String location) {
        super(fuelType, seatingCapacity, fuelCapacity, fuelConsumption);
        this.cargoVolume = cargoVolume;
        this.maxWeight = maxWeight;
        this.height = height;
        this.location = location;
    }

    public Truck(String brand, String model, String madeInCountry, String color, double price, float speed, String fuelType, int seatingCapacity, float fuelCapacity, float fuelConsumption, float cargoVolume, float maxWeight, float height, String location) {
        super(brand, model, madeInCountry, color, price, speed, fuelType, seatingCapacity, fuelCapacity, fuelConsumption);
        this.cargoVolume = cargoVolume;
        this.maxWeight = maxWeight;
        this.height = height;
        this.location = location;
    }

    //Methods
    public void seeLocation(){
        System.out.println(this.location);
    }

}
