import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String text = "Hello PDP Academy and students.";
        System.out.println("Text : " + text);

        MyInterface operation = (str) -> {
            System.out.print("Qidirilayotgan matnni kiriting: ");
            String findingText = scan.nextLine();
            int count = 0;

            for (int i = 0; i < str.length() - findingText.length(); i++) {
                if (str.substring(i, i + findingText.length()).equals(findingText)) count++;
            }
            if(count != 0){
                System.out.println("Qidirilayotgan matn textni ichida bor. ");
            } else {
                System.out.println("Qidirilayotgan matn textni ichida yo'q. ");
            }


        };

        operation.findText(text);


    }
}
