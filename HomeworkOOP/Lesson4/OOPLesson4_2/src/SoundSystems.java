public class SoundSystems extends ElectronicItems{
    //Properties
    float loudness;
    float frequancy;
    boolean isWireless;

    //Constructors
    public SoundSystems() {
    }

    public SoundSystems(float loudness, float frequancy, boolean isWireless) {
        this.loudness = loudness;
        this.frequancy = frequancy;
        this.isWireless = isWireless;
    }

    public SoundSystems(String brand, String model, double price, String madeInCountry, float loudness, float frequancy, boolean isWireless) {
        super(brand, model, price, madeInCountry);
        this.loudness = loudness;
        this.frequancy = frequancy;
        this.isWireless = isWireless;
    }

    //Methods


}
