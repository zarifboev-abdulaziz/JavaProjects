public class Fish extends Animal implements Pet{
    //fields
    String name;

    //Constructor
    public Fish() {
    }

    //Methods
    void eat(){
        System.out.println("Fish is eating.");
    }

    void walk(){
        System.out.println("Fish is swimming.");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        System.out.println("Cat is playing.");
    }
}
