import java.util.Scanner;

public class Circle extends Figure{
Scanner scan = new Scanner(System.in);
    //Methods

    @Override
    public void area() {
        System.out.print("Enter radius of circle: ");
        float radius = scan.nextFloat();
        System.out.println("Area of circle is: " + (float)Math.PI*radius*radius);
    }

    @Override
    public void perimeter(){
        System.out.print("Enter radius of circle: ");
        float radius = scan.nextFloat();
        System.out.println("Length of cicle is : " + (float)2*Math.PI*radius);

    }

}
