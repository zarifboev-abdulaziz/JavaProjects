import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {


        HashSet<String> stringHashSet = new HashSet<>();
        //Task-1
        stringHashSet.add("Bir");
        stringHashSet.add("Ikki");
        stringHashSet.add("Uch");
        stringHashSet.add("To'rt");
        stringHashSet.add("Besh");
        stringHashSet.add("Olti");
        stringHashSet.add("Yetti");
        stringHashSet.add("Sakkiz");
        stringHashSet.add("To'qqiz");
        stringHashSet.add("O'n");

        //Task-2
        Iterator<String> stringIterator = stringHashSet.iterator();
        while (stringIterator.hasNext()){
            System.out.println(stringIterator.next());
        }

        //Task-3
        System.out.println();
        stringHashSet.forEach(s -> System.out.print("For each: " + s));







    }
}
