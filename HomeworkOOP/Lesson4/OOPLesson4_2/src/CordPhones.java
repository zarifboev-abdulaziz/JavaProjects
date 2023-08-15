public class CordPhones extends ElectronicItems {
//Properties;
    boolean isCorded;
    float size;

    //Costructors
    public CordPhones() {
    }

    public CordPhones(boolean isCorded, float size) {
        this.isCorded = isCorded;
        this.size = size;
    }

    public CordPhones(String brand, String model, double price, String madeInCountry, boolean isCorded, float size) {
        super(brand, model, price, madeInCountry);
        this.isCorded = isCorded;
        this.size = size;
    }

    //Methods


}
