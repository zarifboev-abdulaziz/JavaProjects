public class Cow extends Pet{
    //Properties
    boolean isMammal = true;

    //Constructors
    public Cow() {
    }

    public Cow(boolean isMammal) {
        this.isMammal = isMammal;
    }

    public Cow(String petName, boolean isMammal) {
        super(petName);
        this.isMammal = isMammal;
    }

    public Cow(int legNumber, String colour, Boolean isMammal, String petName, boolean isMammal1) {
        super(legNumber, colour, isMammal, petName);
        this.isMammal = isMammal1;
    }

    //Methods


}
