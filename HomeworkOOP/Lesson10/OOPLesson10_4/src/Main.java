import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>(Arrays.asList("Abror", "Akmal", "Bobur", "Bekzod", "Dildor", "Elyor", "Orif", "Umid", "Zarif", "Yo'ldosh"));

        //Sorting names by letters
        names.sort( Comparator.comparing( String::toString ) );

        System.out.println(names);


//Deleting Names which starts with the letter "A"
        for (int i = 0; i < names.size(); i++) {
            if(names.get(i).charAt(0) == 'A'){
                names.remove(i);
                i = i - 1;
            }
        }


        System.out.println(names);


    }

}
