import java.util.HashMap;
import java.util.Scanner;

public class Main {

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
            System.out.println("1.ProductList\n2.Total cost\n3.Update product\n4.Add Product\n5.Remove Product\n6.Exit");
            int option = scannerInt.nextInt();
            switch (option){
                case 1: {
                    System.out.println("Product list: ");
                    productList.forEach((s, product5) -> System.out.println(product5));
                    System.out.println("To'xtash uchun 0 ni kiriting. ");

                    while (true){
                        System.out.println("Enter product name: ");
                        String inputProductName = scannerStr.nextLine();
                        if(inputProductName.equals("0")){break;}
                        System.out.println("Enter quantity: ");
                        int inputQuantity = scannerInt.nextInt();

                        for (String findproduct : productList.keySet()) {
                            if(inputProductName.equalsIgnoreCase(findproduct)){
                                System.out.println("Olindi");
                                sum = sum + productList.get(findproduct).getProductValue()*inputQuantity;
                            }
                        }
                    }


                } break;
                case 2: {
                    System.out.println("Total cost: " + sum);

                } break;
                case 3: {
                    System.out.println("Product list: ");
                    productList.forEach((s, product5) -> System.out.println(product5));
                    System.out.print("Enter the name of the product which u wanna change: ");
                    String inputProductName = scannerStr.nextLine();
                    System.out.print("Enter new name of the product: ");
                    String newProductName = scannerStr.nextLine();
                    System.out.println("Enter new price: ");
                    double inputProductPrice = new Scanner(System.in).nextDouble();

                    for (String keyProduct : productList.keySet()) {
                        if (keyProduct.equalsIgnoreCase(inputProductName)) {
                            productList.get(keyProduct).setProductName(newProductName);
                            productList.get(keyProduct).setProductValue(inputProductPrice);
                        }
                    }

                } break;
                case 4: {
                    System.out.println("===Add Product===");
                    System.out.print("Enter product name: ");
                    String inputProductName = scannerStr.nextLine();
                    System.out.println("Enter product price: ");
                    double inputProductPrice = new Scanner(System.in).nextDouble();

                    Product product5 = new Product(inputProductName, inputProductPrice);
                    productList.put(product5.getProductName(), product5);
                    System.out.println("Successfully added.");
                } break;
                case 5: {
                    System.out.println("Product list: ");
                    productList.forEach((s, product5) -> System.out.println(product5));
                    System.out.println("===Remove Product===");
                    System.out.println("Enter product name in order to remove: ");
                    String inputProductName = scannerStr.nextLine();

                    for (String deletingProduct : productList.keySet()) {
                        if(deletingProduct.equalsIgnoreCase(inputProductName)){
                            productList.remove(inputProductName);
                            break;
                        }
                    }
                    System.out.println("Successfully deleted");

                } break;
                case 6: {
                    active = false;
                } break;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }
}
