import java.util.Scanner;

public class SortValue {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Long toifasida son kiriting: ");
        long n = scan.nextLong();

        if(n <= Byte.MAX_VALUE){
            byte numByte = (byte)n;
            System.out.println("Son Byte toifasida xotirada saqlandi: " + n);
        } else if(n <= Short.MAX_VALUE){
            short shortNum = (short)n;
            System.out.println("Son Short toifasida xotirada saqlandi: " + n);
        } else if (n <= Integer.MAX_VALUE){
            int intNum = (int)n;
            System.out.println("Son int toifasida xotirada saqlandi: " + n);
        } else {
            long numLong = n;
            System.out.println("Son Long toifasida xotirada saqlandi: " + n);
        }


    }
}
