import java.util.Scanner;

public class Savol3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Matn kiriting: ");
        String text = scan.nextLine();

        System.out.println(uchliklar(text));


    }

    public static int uchliklar(String str){
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if(i < str.length()-2 && str.charAt(i) == str.charAt(i + 1) && str.charAt(i+1) == str.charAt(i + 2)){
                count++;
            }
        }
        return count;
    }

}
