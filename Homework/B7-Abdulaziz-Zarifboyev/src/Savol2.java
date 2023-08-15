import java.util.Scanner;

public class Savol2 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Son kiriting:");
        int n = scan.nextInt();

        int count = n;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i ; j++) {
                if(j==i){System.out.print(count); count--;
                } else {
                    System.out.print("  ");
                }

            }
            System.out.println();
        }

        for (int i = 1; i <= n-1 ; i++) {
            for (int j = n-1; j >= i ; j--) {
                if(j == i){
                    System.out.print(count + 2); count++;
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }


    }
}
