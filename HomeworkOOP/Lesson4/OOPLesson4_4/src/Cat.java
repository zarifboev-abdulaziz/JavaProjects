public class Cat extends  Pet{
    //Properties
    boolean isMammal = true;

    //Constructors
    public Cat() {
    }

    public Cat(boolean isMammal) {
        this.isMammal = isMammal;
    }

    public Cat(String petName, boolean isMammal) {
        super(petName);
        this.isMammal = isMammal;
    }

    public Cat(int legNumber, String colour, Boolean isMammal, String petName, boolean isMammal1) {
        super(legNumber, colour, isMammal, petName);
        this.isMammal = isMammal1;
    }
    //Methods


}
