package uz.pdp;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import uz.pdp.model.Task;
import uz.pdp.model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataBase {

    public static List<Task> taskList = new ArrayList<>();

    public static void getDataFromJson() throws FileNotFoundException {
        Reader reader = new FileReader("src/main/resources/todos.json");
        Gson gson = new Gson();

        taskList = gson.fromJson(reader, new TypeToken<List<Task>>(){}.getType());

    }

    public static User getUserFromJson(){
        System.out.println("Enter User Id: ");
        long inputId = new Scanner(System.in).nextLong();

        for (Task task : taskList) {
            if (task.getUserId() == inputId){
                return new User(task.getUserId());
            }
        }

        return null;
    }



}
