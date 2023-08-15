public class MobilePhones extends Phones{
    //Properties
    int memory;
    int Ram;
    int camera;

    //Constructors
    public MobilePhones() {
    }

    public MobilePhones(int memory, int ram, int camera) {
        this.memory = memory;
        Ram = ram;
        this.camera = camera;
    }

    public MobilePhones(float weight, float display, float battery, float size, int memory, int ram, int camera) {
        super(weight, display, battery, size);
        this.memory = memory;
        Ram = ram;
        this.camera = camera;
    }

    public MobilePhones(String brand, String model, double price, String madeInCountry, float weight, float display, float battery, float size, int memory, int ram, int camera) {
        super(brand, model, price, madeInCountry, weight, display, battery, size);
        this.memory = memory;
        Ram = ram;
        this.camera = camera;
    }

    //Methods


}
