public class Earplugs extends SoundSystems {
//Properties
    boolean isForGaming;

    //Constructors
    public Earplugs() {
    }

    public Earplugs(boolean isForGaming) {
        this.isForGaming = isForGaming;
    }

    public Earplugs(float loudness, float frequancy, boolean isWireless, boolean isForGaming) {
        super(loudness, frequancy, isWireless);
        this.isForGaming = isForGaming;
    }

    public Earplugs(String brand, String model, double price, String madeInCountry, float loudness, float frequancy, boolean isWireless, boolean isForGaming) {
        super(brand, model, price, madeInCountry, loudness, frequancy, isWireless);
        this.isForGaming = isForGaming;
    }

    //Methods


}
