public class Rectangle extends Shape{
    //fields
    double length;
    double width;

    //Constructors
    public Rectangle() {
    }

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    //Methods
    @Override
    public double area() {
        return this.length*this.length;
    }

    @Override
    public String getColour() {
        return super.getColour();
    }
}
