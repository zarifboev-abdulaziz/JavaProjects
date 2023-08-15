public class Dog implements Animal{
    //Fields
    String color;
    String name;


    @Override
    public void sound() {
        System.out.println("Dog is barking");
    }

    @Override
    public void eat() {
        System.out.println("Dog is eating");
    }

    public void run(){
        System.out.println("Dog is running");
    }

    public void guard(){
        System.out.println("Dog is guarding");
    }

}
