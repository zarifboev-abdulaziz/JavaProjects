import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.print("Task-1 : ");
        List list = new ArrayList(Arrays.asList("Abror", "Asad", "Avaz", "Axror", "Aziz", "Akrom", "Akmal", "Adham", "Abdulloh", "Bobur"));
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + "  ");
        }


        System.out.print("\nTask-2 : ");
        list.add("Xolmat");
        System.out.println(list);

        System.out.print("\nTask-3 : ");
        list.add(0, "Abdulaziz");
        System.out.println(list);

        System.out.print("\nTask-4 : ");
        //Task-4
        list.remove(2);
        list.add(2,"Zarifboyev");

        list.remove(3);
        list.add(3,"Zarifboyev");
        System.out.println(list);

        System.out.print("\nTask-5 : ");
        //Task-5
        Object[] objects = list.toArray();
        System.out.println(Arrays.toString(objects));

        System.out.print("\nTask-6 : ");
        //Task-6
        list.remove("Abdulaziz");
        list.remove("Zarifboyev");
        System.out.println(list);



    }
}
