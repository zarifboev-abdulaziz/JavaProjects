public class Bird extends Animal{
    //Properties
    float wingLength;
    boolean canFly;

    //Constructors
    public Bird() {
    }

    public Bird(float wingLength, boolean canFly) {
        this.wingLength = wingLength;
        this.canFly = canFly;
    }

    public Bird(int legNumber, String colour, Boolean isMammal, float wingLength, boolean canFly) {
        super(legNumber, colour, isMammal);
        this.wingLength = wingLength;
        this.canFly = canFly;
    }

    //Methods


}
