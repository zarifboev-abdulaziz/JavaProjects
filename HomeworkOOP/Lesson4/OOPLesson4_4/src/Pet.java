public class Pet extends  Animal{
    //Properties
    String petName;

    //Constructors
    public Pet() {
    }

    public Pet(String petName) {
        this.petName = petName;
    }

    public Pet(int legNumber, String colour, Boolean isMammal, String petName) {
        super(legNumber, colour, isMammal);
        this.petName = petName;
    }

    //Methods

}
