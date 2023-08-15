package uz.pdp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import uz.pdp.model.Car;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();

        File file = new File("Test.json");

        try (Reader reader = new FileReader(file);
             Writer writer = new FileWriter(file, true);)
        {



            List<Car> carList = new ArrayList<>(Arrays.asList(
                    new Car(12, "Audi", "GTR"),
                    new Car(13, "Chevrolet", "Camaro ss")
            ));


            String value = gson.toJson(carList);


            writer.write(value);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

