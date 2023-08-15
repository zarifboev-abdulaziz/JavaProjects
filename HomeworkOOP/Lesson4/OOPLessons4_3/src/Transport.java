public class Transport {
    //Properties
    String brand;
    String model;
    String madeInCountry;
    String color;
    double price;
    float speed;

    //Constructors
    public Transport() {
    }
    public Transport(String brand, String model, String madeInCountry, String color, double price, float speed) {
        this.brand = brand;
        this.model = model;
        this.madeInCountry = madeInCountry;
        this.color = color;
        this.price = price;
        this.speed = speed;
    }

    //Methods
    public void driving(){
        System.out.println("The transport is driving");
    }

    public void Stopped(){
        System.out.println("The transport is stopped");
    }


}
