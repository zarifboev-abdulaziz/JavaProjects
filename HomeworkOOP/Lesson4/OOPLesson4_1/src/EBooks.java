public class EBooks extends Books{
    //Properties
    String websiteName;
    String bookSize; //(MB)

    //constructor
    public EBooks() {
    }

    public EBooks(String websiteName, String bookSize) {
        this.websiteName = websiteName;
        this.bookSize = bookSize;
    }

    public EBooks(String author, double price, double discount, String title, int totalPages, String websiteName, String bookSize) {
        super(author, price, discount, title, totalPages);
        this.websiteName = websiteName;
        this.bookSize = bookSize;
    }

    //Methods
    public void printEBookTitle(){
        System.out.println(this.title);
    }


}
