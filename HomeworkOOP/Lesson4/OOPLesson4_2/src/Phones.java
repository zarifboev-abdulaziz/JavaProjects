public class Phones extends ElectronicItems{
    //Properties
    float weight;
    float display;
    float battery;
    float size;

    //Constructors
    public Phones() {
    }

    public Phones(float weight, float display, float battery, float size) {
        this.weight = weight;
        this.display = display;
        this.battery = battery;
        this.size = size;
    }

    public Phones(String brand, String model, double price, String madeInCountry, float weight, float display, float battery, float size) {
        super(brand, model, price, madeInCountry);
        this.weight = weight;
        this.display = display;
        this.battery = battery;
        this.size = size;
    }

    //Methods


}
