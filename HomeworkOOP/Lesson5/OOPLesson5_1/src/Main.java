import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Figure figure = new Figure();
        Scanner scan = new Scanner(System.in);

        System.out.print("Aylanining radiusini kiriting: ");
        float r = scan.nextFloat();
        figure.area(r);

        System.out.println("To'g'ri to'rtburchakning tomonlarini kiriting: ");
        System.out.print("a = ");
        float a = scan.nextFloat();
        System.out.print("b = ");
        float b = scan.nextFloat();

        figure.area(a, b);




    }
}
