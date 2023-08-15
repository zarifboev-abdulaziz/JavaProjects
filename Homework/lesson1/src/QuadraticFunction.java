import java.util.Scanner;

public class QuadraticFunction {
    public static void main(String[] args) {
        System.out.println("y = a*x^2 + b*x + c\nFunksiyaning koeffitsientlarini kiriting: ");
        Scanner scan = new Scanner(System.in);
        System.out.print("a = ");
        float a = scan.nextFloat();

        System.out.print("b = ");
        float b = scan.nextFloat();

        System.out.print("c = ");
        float c = scan.nextFloat();

        float d = b*b - 4*a*c;
        float x1 = 0, x2 = 0;
        if(d < 0){
            System.out.println("Bo'sh to'plam");
        } else if (d > 0){
            x1 = (float) ((-b+Math.sqrt(d))/2*a);
            x2 = (float) ((-b-Math.sqrt(d))/2*a);
            System.out.println("Javoblari: " + x1 + " va " + x2);
        } else {
            x2 = (float) ((-b-Math.sqrt(d))/2*a);
            System.out.println("Diskriminant 0ga teng va javobi: " + x2);
        }



    }
}
