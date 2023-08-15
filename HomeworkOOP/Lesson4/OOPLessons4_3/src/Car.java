public class Car extends Vehicle {
    //Properties
    float weight;


    //Constructors
    public Car() {
    }

    public Car(float weight) {
        this.weight = weight;
    }

    public Car(String fuelType, int seatingCapacity, float fuelCapacity, float fuelConsumption, float weight) {
        super(fuelType, seatingCapacity, fuelCapacity, fuelConsumption);
        this.weight = weight;
    }

    public Car(String brand, String model, String madeInCountry, String color, double price, float speed, String fuelType, int seatingCapacity, float fuelCapacity, float fuelConsumption, float weight) {
        super(brand, model, madeInCountry, color, price, speed, fuelType, seatingCapacity, fuelCapacity, fuelConsumption);
        this.weight = weight;
    }

    //Methods


}
