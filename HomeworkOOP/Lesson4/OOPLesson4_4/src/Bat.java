public class Bat extends Bird{
    //Properties
    boolean canFly = true;
    boolean isMammal = true;

    //Constructors
    public Bat() {
    }

    public Bat(boolean canFly, boolean isMammal) {
        this.canFly = canFly;
        this.isMammal = isMammal;
    }

    public Bat(float wingLength, boolean canFly, boolean canFly1, boolean isMammal) {
        super(wingLength, canFly);
        this.canFly = canFly1;
        this.isMammal = isMammal;
    }

    public Bat(int legNumber, String colour, Boolean isMammal, float wingLength, boolean canFly, boolean canFly1, boolean isMammal1) {
        super(legNumber, colour, isMammal, wingLength, canFly);
        this.canFly = canFly1;
        this.isMammal = isMammal1;
    }

    //Methods


}
