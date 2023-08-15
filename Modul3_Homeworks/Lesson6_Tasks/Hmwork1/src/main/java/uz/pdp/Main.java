package uz.pdp;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import uz.pdp.model.User;
import uz.pdp.servis.TodoService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        TodoService service = new TodoService();

        DataBase.getDataFromJson();
        User userFromJson = DataBase.getUserFromJson();

        if (userFromJson != null){
             service.todoMenu(userFromJson);
        }

    }
}
