import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class KorzinkaApp {
    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);


        HashMap<String, Product> productList = new HashMap<>(); //(o1, o2) -> o1.compareTo(o2)
        HashMap<String, Product> savatcha = new HashMap();

        //Prodects
        Product product = new Product("Olma", 10000);
        Product product1 = new Product("Go'sht", 60000);
        Product product2 = new Product("Yog'", 20000);
        Product product3 = new Product("Chocolate", 14000);
        Product product4 = new Product("Banan", 20000);

        productList.put(product.getProductName(), product);
        productList.put(product1.getProductName(), product1);
        productList.put(product2.getProductName(), product2);
        productList.put(product3.getProductName(), product3);
        productList.put(product4.getProductName(), product4);

        boolean active = true;
        double sum = 0;
        while (active){
            System.out.println("1.ProductList\n2.Total cost\n3.exit");
            int option = scannerInt.nextInt();
            switch (option){
                case 1: {
                    System.out.println("Product list: ");
                   productList.forEach((s, product5) -> System.out.println(product5));

                    System.out.println("Enter product name: ");
                    String inputProductName = scannerStr.nextLine();

                    for (String findproduct : productList.keySet()) {
                        if(inputProductName.equalsIgnoreCase(findproduct)){
                            System.out.println("Olindi");
                            sum = sum + productList.get(findproduct).getProductValue();
                        }
                    }

                } break;
                case 2: {
                    System.out.println("Total cost: " + sum);

                } break;
                case 3: {
                    active = false;
                } break;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }







    }
}
