public class Books {
    //Properties
    protected String author;
    protected double price;
    protected double discount;
    protected String title;
    protected int totalPages;

    //Constructors
    public Books() {
    }
    public Books(String author, double price, double discount, String title, int totalPages) {
        this.author = author;
        this.price = price;
        this.discount = discount;
        this.title = title;
        this.totalPages = totalPages;
    }

    //Methods
    public void calculateSellingPrice(double price){
        System.out.println("Sotuv narxi: " + price*2);
    }

    public void calculateCustomsPrice(){
       //Sotuv narxini 30% foizi
        System.out.println("Bojxona narxi: " + price*2*0.3);

    }




}
