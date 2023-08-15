public class Eagle extends Bird{
    //Properties
    boolean canFly = true;


    //Constructors
    public Eagle() {
    }

    public Eagle(boolean canFly) {
        this.canFly = canFly;
    }

    public Eagle(float wingLength, boolean canFly, boolean canFly1) {
        super(wingLength, canFly);
        this.canFly = canFly1;
    }

    public Eagle(int legNumber, String colour, Boolean isMammal, float wingLength, boolean canFly, boolean canFly1) {
        super(legNumber, colour, isMammal, wingLength, canFly);
        this.canFly = canFly1;
    }

    //Methods


}
