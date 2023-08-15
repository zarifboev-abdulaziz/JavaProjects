import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanStr = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        Book book1 = new Book();

        System.out.print("Enter the name of the book: ");
        String name = scanStr.nextLine();

        System.out.print("Enter the number of authors of this book: ");
        int n = scanInt.nextInt();

        String[] authors = new String[n];
        for (int i = 0; i < authors.length ; i++) {
            System.out.print("Enter author " + (i + 1) + " : ");
            authors[i] = scanStr.nextLine();
        }

        book1.printNameAuthors(name, authors);


    }
}
