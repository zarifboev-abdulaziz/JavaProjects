public class Cat extends Animal implements Pet{
    //fields
    String name;


    //Constructor
    public Cat() {
    }
    public Cat(String name) {
    }

    //Methods
    void eat(){
        System.out.println("Cat is eating.");
    }

    //
    @Override
    public void play() {
        System.out.println("Cat is playing.");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
