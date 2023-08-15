import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        MyInterface sign = (a) -> {
            if(a > 0){
                System.out.println("Bu musbat son.");
            } else if(a < 0){
                System.out.println("Bu manfiy son");
            } else {
                System.out.println("nol ");
            }
        };

        System.out.print("Son kiriting: ");
        sign.signNum(scan.nextInt());

    }
}
