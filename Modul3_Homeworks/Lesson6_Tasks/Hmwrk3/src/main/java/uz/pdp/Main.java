package uz.pdp;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import uz.pdp.model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader("src/main/resources/users.json");
        List<uz.pdp.model.User> userList = gson.fromJson(reader, new TypeToken<List<uz.pdp.model.User>>(){}.getType());

        System.out.println("Foydalanuvchini userName yoki emailini kiriting: ");
        String inputContact = new Scanner(System.in).nextLine();

        for (User user : userList) {
            if (user.getUsername().equals(inputContact) || user.getEmail().equals(inputContact)){
                String info = "User Company: " + user.getCompany().getName() + "\n" +
                        "Location: \n"  + "Latitude: " + user.getAddress().getGeo().getLat() + ", Longitude: " + user.getAddress().getGeo().getLng();

                System.out.println(info);
            }
        }

    }
}
