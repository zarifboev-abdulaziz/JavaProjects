public class Dog extends Pet{
    //Properties
    boolean isMammal = true;

    //Constructors
    public Dog() {
    }

    public Dog(boolean isMammal) {
        this.isMammal = isMammal;
    }

    public Dog(String petName, boolean isMammal) {
        super(petName);
        this.isMammal = isMammal;
    }

    public Dog(int legNumber, String colour, Boolean isMammal, String petName, boolean isMammal1) {
        super(legNumber, colour, isMammal, petName);
        this.isMammal = isMammal1;
    }

    //Methods


}
