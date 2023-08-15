package uz.pdp.service.clientService;

import uz.pdp.model.Cloth;
import uz.pdp.service.clientService.interfaces.FilterClothesService;
import uz.pdp.utils.Db;
import uz.pdp.utils.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class FilterClothesServiceImpl implements FilterClothesService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    @Override
    public void filterClothesMenu() {

        Util.print(Util.CYAN, "1=> Clothes in Discount 2=> Filter by Price 3=> Ascending order 4=> Descending order 5=> Back");
        int option = scannerInt.nextInt();
        switch (option){
            case 1:
                clothesInDiscount();
                break;
            case 2:
                filterByPrice();
                break;
            case 3:
                ascendingOrder();
                break;
            case 4:
                descendingOrder();
                break;

            case 5: return;
            default:
                System.err.println("Wrong option!!!");
                break;
        }
        filterClothesMenu();
    }

    @Override
    public void clothesInDiscount() {
        List<Cloth> clothDiscountList = Db.clothList.stream().filter(cloth -> cloth.getDiscount() > 0).collect(Collectors.toList());
        clothDiscountList.forEach(cloth -> System.out.println(cloth));

    }

    @Override
    public void filterByPrice() {
        System.out.println("Enter price: ");
        double price = scannerInt.nextDouble();

        Db.clothList.stream()
                .filter(cloth -> (cloth.getPrice() - cloth.getPrice()*cloth.getDiscount()/100) <= price)
                .forEach(cloth -> System.out.println(cloth));

    }

    @Override
    public void ascendingOrder() {

        Db.clothList.stream().sorted(Comparator.comparing(Cloth::getPrice)).forEach(System.out::println);

    }

    @Override
    public void descendingOrder() {

        Db.clothList.stream().sorted(Comparator.comparing(Cloth::getPrice).reversed()).forEach(System.out::println);

    }
}
