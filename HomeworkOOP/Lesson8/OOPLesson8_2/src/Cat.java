public class Cat implements Animal{
    //fields
    String color;
    String name;
    double weight;
    double height;


    //Methods
    @Override
    public void sound() {
        System.out.println("Cat is meowing");
    }

    @Override
    public void eat() {
        System.out.println("Cat is eating");
    }

    public void run(){
        System.out.println("Cat is running");
    }
}
