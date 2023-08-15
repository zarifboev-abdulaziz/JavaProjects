import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        //Task-2
        HashMap<String, Double> fruitHashMap = new HashMap();

        //Task-3
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


        //4-Task
        Set entrySet = fruitHashMap.entrySet();

        Iterator iterator = entrySet.iterator();

        int count = 0;
        while (iterator.hasNext()){
            iterator.next();
            count++;
        }
        System.out.println("Ro'yxatda " +count+ " ta meva bor.");

        //Task-5
        fruitHashMap.forEach((meva, narx) -> System.out.println(meva + " : " + narx + "$"));








    }
}
