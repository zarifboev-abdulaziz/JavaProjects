import java.util.Scanner;

public class Rectangle extends Figure{
    Scanner scan = new Scanner(System.in);

    //Methods
    @Override
    public void area(){
        System.out.print("Enter side A : ");
      float sideA = scan.nextFloat();
        System.out.print("Enter side B : ");
      float sideB = scan.nextFloat();
        System.out.println("Area of Rectangle: " + sideA*sideB);
    }

    @Override
    public void perimeter(){
        System.out.print("Enter side A : ");
        float sideA = scan.nextFloat();
        System.out.print("Enter side B : ");
        float sideB = scan.nextFloat();
        System.out.println("Area of Rectangle: " + sideA+sideB);

    }
}
