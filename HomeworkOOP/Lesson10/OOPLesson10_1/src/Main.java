import java.util.*;

public class Main {

    public static void main(String[] args) {


        System.out.println("circle 1 ");
        List<Circle> circle1 = new ArrayList(Arrays.asList(12, 23, 33, 13, 123));
        System.out.println(circle1);

        System.out.println("circle 2 ");
        List<Circle> circle2 = new ArrayList<>(circle1);
        System.out.println(circle2);
    }
}
