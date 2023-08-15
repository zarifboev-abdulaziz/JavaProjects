public class Fruit {
    //Task-1
    //Fields
    String name;
    String type;
    double sale;

    //Constructors
    public Fruit() {
    }

    public Fruit(String name, String type, double sale) {
        this.name = name;
        this.type = type;
        this.sale = sale;
    }

    //Getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    //to String

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sale=" + sale +
                '}';
    }
}
