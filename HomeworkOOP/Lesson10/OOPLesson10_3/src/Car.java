public class Car {
    String colour;
    String model;
    String carNumber;

    //Constructor
    public Car() {
    }

    public Car(String colour, String model, String carNumber) {
        this.colour = colour;
        this.model = model;
        this.carNumber = carNumber;
    }


    //Getters and Setters

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public String toString() {
        return "Car{" +
                "colour='" + colour + '\'' +
                ", model='" + model + '\'' +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }
}
