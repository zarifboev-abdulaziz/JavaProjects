public class Product {
    String productName;
    double productValue;

    public Product() {
    }

    public Product(String productName, double productValue) {
        this.productName = productName;
        this.productValue = productValue;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductValue() {
        return productValue;
    }

    public void setProductValue(double productValue) {
        this.productValue = productValue;
    }

    @Override
    public String toString() {
        return "product Name: " + productName  +
                ", product Value : " + productValue + " som";
    }
}
