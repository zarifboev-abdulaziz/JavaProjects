public class Straus extends  Bird{
    //Properties
    boolean canFly = false;

    //Constructors
    public Straus() {
    }

    public Straus(boolean canFly) {
        this.canFly = canFly;
    }

    public Straus(float wingLength, boolean canFly, boolean canFly1) {
        super(wingLength, canFly);
        this.canFly = canFly1;
    }

    public Straus(int legNumber, String colour, Boolean isMammal, float wingLength, boolean canFly, boolean canFly1) {
        super(legNumber, colour, isMammal, wingLength, canFly);
        this.canFly = canFly1;
    }

    //Methods


}
