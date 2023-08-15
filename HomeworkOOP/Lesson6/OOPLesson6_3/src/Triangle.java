import java.util.Scanner;

public class Triangle extends Figure{
    Scanner scan = new Scanner(System.in);


    //Methods
    @Override
    public void area(){
        System.out.print("Enter side A : ");
        float sideA = scan.nextFloat();
        System.out.print("Enter side B : ");
        float sideB = scan.nextFloat();
        System.out.println("Area of Triangle: " + 0.5*sideA*sideB);


    }
    @Override
    public void perimeter(){
        System.out.print("Enter side A : ");
        float sideA = scan.nextFloat();
        System.out.print("Enter side B : ");
        float sideB = scan.nextFloat();
        System.out.print("Enter hipotenuse : ");
        float hipotenuse = scan.nextFloat();

        System.out.println("Perimeter of triangle: " + (sideA + sideB + hipotenuse));
    }
}
