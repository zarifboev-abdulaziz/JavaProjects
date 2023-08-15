import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int max = 0;

        int n = scanner.nextInt();
        if (n > max){max = n;}
        do {
            n = scanner.nextInt();
            if(n > max){
                max = n;
            }
        }while (n != 0);

        System.out.println(max);

    }
}
