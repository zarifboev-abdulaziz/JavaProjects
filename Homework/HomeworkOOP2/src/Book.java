import java.util.Arrays;

public class Book {
    //Properties
    private String name;
    private String[] authors;
    private long isbn;
    private float price;

    //Constructors
    public Book() {
    }

    public Book(String name, String[] authors, long isbn, float price) {
        this.name = name;
        this.authors = authors;
        this.isbn = isbn;
        this.price = price;
    }

    //Methods
    void printNameAuthors(String name, String[] authors){
        System.out.println("Name of the book: " + name);
        System.out.println("Authors of the book: " + Arrays.toString(authors));
    }


    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
