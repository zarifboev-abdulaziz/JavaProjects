import java.util.HashMap;
import java.util.Scanner;

public class Process {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter country name: ");
        String countryName = scanner.nextLine();

        HashMap<String, Country> countryHashMap = new HashMap();

        Country country = new Country("Uzbekistan", "Tashkent", 5600000, 35000000);
        Country country1 = new Country("Tojikiston", "Dushanbe", 70000000, 47000000);
        Country country2 = new Country("Qirgisizton", "Bishkek", 9000000, 9888888);

        countryHashMap.put(country.getName(), country);
        countryHashMap.put(country1.getName(), country1);
        countryHashMap.put(country2.getName(), country2);



        for (String name : countryHashMap.keySet()) {
            if(name.equals(countryName)){
                System.out.println(countryHashMap.get(name).toString());

            }
        }



    }
}
