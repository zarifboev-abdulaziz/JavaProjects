public class Circle extends Shape{
    //Field
    double radius;

    //Constructor
    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    //methods
    @Override
    public double area() {
        return (Math.PI * this.radius * this.radius);
    }

    @Override
    public String getColour() {
        return super.getColour();
    }
}
