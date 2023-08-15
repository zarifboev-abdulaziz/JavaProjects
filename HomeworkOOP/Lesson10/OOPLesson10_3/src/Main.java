import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Car car1 = new Car("Black", "Camaro", "01 777 UZB");
        Car car2 = new Car("White", "Malibu", "07 707 UZB");
        Car car3 = new Car("Blue", "Matiz", "09 099 UBF");
        Car car4 = new Car("Metallic", "Nexia", "98 987 IUY");
        Car car5 = new Car("Ground", "Tracker", "77 879, UIO");
        Car car6 = new Car("Green", "Orlando", "45 567 QWE");
        Car car7 = new Car("Orange", "Maliba 2", "65 655 TTY");
        Car car8 = new Car("Ketmon", "Uchar", "01 000 OOO");
        Car car9 = new Car("Blue", "BMW", "09 090 OIU");
        Car car10 = new Car("Yellow", "Spark", "00 000 UUU");

        List<Car> cars = new ArrayList<>(Arrays.asList(car1, car2, car3, car4, car5, car6, car7, car8, car9, car10));

        //Task- 2
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i).toString());
        }

        System.out.println();
        System.out.println("Task-3");
        getRandomCar(cars);

        System.out.println();
        System.out.println("Task-4");
        List<Car> cars1 = new ArrayList<>(Arrays.asList(car3, car4, car5, car6, car7));

        for (int i = 0; i < cars1.size(); i++) {
            System.out.println(cars1.get(i).toString());
        }

        System.out.println();
        System.out.println("Task-5 sorted Cars by Model");

        cars.sort(Comparator.comparing(Car::getModel));
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i).toString());
        }

        System.out.println();
        System.out.println("Task-6");
        cars.removeAll(cars1);
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i).toString());
        }

        System.out.println();
        System.out.println("Task-7");
        cars.retainAll(cars1);
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i).toString());
        }









    }
    public static void getRandomCar(List list){
        System.out.println(list.get((int)(Math.random()*10)).toString());
    }



}
