import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>(50);


        for (int i = 0; i < 50; i++) {
            numbers.add((int)(Math.random()*100));
        }

        System.out.println(numbers.size());

        //(int)(Math.random()*100)
        System.out.println(numbers);



    }
}
