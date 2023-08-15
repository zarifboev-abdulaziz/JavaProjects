import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        HashMap<String, Double> fruitHashMap = new HashMap();

        fruitHashMap.put("Anor, Quva", 15000.0);
        fruitHashMap.put("Anor, Tuya tish", 16000.0);
        fruitHashMap.put("Anor, Surxon", 14000.0);
        fruitHashMap.put("Olma, Besh Yulduz", 9000.0);
        fruitHashMap.put("Olma, Golden", 8000.0);
        fruitHashMap.put("Olma, Qirmizi", 15000.0);
        fruitHashMap.put("Olma, Semerenka", 6000.0);
        fruitHashMap.put("Banan 5% chegirma, Bananza", 30000.0);
        fruitHashMap.put("Banan 5% chegirma, Boshqa", 20000.0);
        fruitHashMap.put("Shaftoli, Tukli", 6000.0);
        fruitHashMap.put("Shaftoli, Tuksiz", 8000.0);
        fruitHashMap.put("Kivi, Import", 21000.0);
        fruitHashMap.put("Kivi, Mahalliy", 20000.0);
        fruitHashMap.put("Qulupnay, Yirik", 18000.0);
        fruitHashMap.put("Qulupnay, Mayda", 9000.0);
        fruitHashMap.put("Uzum, Qora", 10000.0);
        fruitHashMap.put("Uzum, Husayni", 15000.0);
        fruitHashMap.put("O'rik, Surxon", 3000.0);
        fruitHashMap.put("O'rik, Oq", 2000.0);
        fruitHashMap.put("O'rik, Yirik", 12000.0);

        //Task-1
        System.out.println("Task-1");
        int i = 0;
        double sum = 0;

        Iterator<String> iterator = fruitHashMap.keySet().iterator();

        while (iterator.hasNext()){
            i++;
            sum = sum + fruitHashMap.get(iterator.next());
        }
        System.out.println("Mevalarning o'rtacha narxi: " + (sum/i));

        //Task-2
        System.out.println("Task-2");
        fruitHashMap.forEach((meva, narx) -> {
            System.out.println("Meva: "  + meva  + ", narxi: "  + narx*1.05);
        });

        //Task-3
        System.out.println("task-3");
        fruitHashMap.forEach((meva, narx) -> {
            System.out.println("Meva: " + meva);
        });

        //Task-4
        System.out.println("Task-4");
        fruitHashMap.forEach((meva, narx) -> {
            System.out.println("Meva: "  + meva  + ", narxi: "  + narx);
        });

        //Task-5
        System.out.println("Task-5");
        fruitHashMap.forEach((meva, narx) -> {
            System.out.println("narxi: "  + narx);
        });








    }
}
