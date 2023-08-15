public abstract class Animal {
    //fields
    int legs;
    String colour;

    //Constructor
    public Animal() {
    }

    public Animal(int legs) {
        this.legs = legs;
    }


    //methods
    void walk(){
        System.out.println("Animal is walking.");
    }
    void eat(){
        System.out.println("Animal is eating.");
    }
}
