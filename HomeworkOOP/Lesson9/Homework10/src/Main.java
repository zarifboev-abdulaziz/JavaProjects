import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        MyInterface operation = (str) -> {
            int countLetter = 0;

            for (int i = 0; i < str.length(); i++) {
                if(str.charAt(i)== 'A' || str.charAt(i)== 'E' || str.charAt(i)== 'U' || str.charAt(i)== 'I' || str.charAt(i)== 'O' ||
                   str.charAt(i)== 'a' || str.charAt(i)== 'e' || str.charAt(i)== 'u' || str.charAt(i)== 'i' || str.charAt(i)== 'o'){
                    countLetter++;
                }
            }

            System.out.println("Kiritilgan matnda Unli harflar soni: " + countLetter);
        };

        System.out.print("Enter a text: ");
        operation.countLetter(scan.nextLine());

    }
}
