public class Cat extends  Animal {
    //Fields
    String name;
    String neededFood;
    String leatherQuality;

    //methods

    @Override
    public void sound() {
        System.out.println("Cat: meow, meow...");
    }
}
