public class Tiger implements Animal{
    String color;
    float weight;
    float height;


    //methods
    @Override
    public void sound() {
        System.out.println("Tiger is making sound");
    }

    @Override
    public void eat() {
        System.out.println("Tiger is eating");
    }

    public void run(){
        System.out.println("Tiger is running");
    }

    public void attack(){
        System.out.println("Tiger is attacking");
    }
}
