import java.util.Scanner;

public class Point {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Doiraning Radiusini kiriting: ");
        float r = scan.nextFloat();



        System.out.println(Math.round(45.56));

        doiraUzunligi(r);
        doiraYuzasi(r);




    }

     public static void doiraUzunligi(float r){
         System.out.println("Doiraning uzunligi: " + 2 * r * (float)Math.PI);
     }

    public static void doiraYuzasi(float r){
        System.out.println("Doiraning Yuzasi: " + r * r * (float)Math.PI);
    }


}
