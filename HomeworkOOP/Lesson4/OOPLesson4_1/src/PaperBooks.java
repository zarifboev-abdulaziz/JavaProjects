public class PaperBooks extends Books{
    //Properties
    float paperBookWeight; // vazni
    float delivery; // Necha kunda yetkazib beradi;

    //constructor

    public PaperBooks() {
    }

    public PaperBooks(float paperBookWeight, float delivery) {
        this.paperBookWeight = paperBookWeight;
        this.delivery = delivery;
    }

    public PaperBooks(String author, double price, double discount, String title, int totalPages, float paperBookWeight, float delivery) {
        super(author, price, discount, title, totalPages);
        this.paperBookWeight = paperBookWeight;
        this.delivery = delivery;
    }

    //Methods

}
