public class Vehicle {
    //fields
    private String name;

    // Constructors
    public Vehicle() {
    }

    public Vehicle(String name) {
        this.name = name;
    }

    //Methods
    public void resource(){
        System.out.println("I use petrol");
    }

    //Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //To string
    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                '}';
    }
}
