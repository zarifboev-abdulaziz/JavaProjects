package uz.pdp;

import uz.pdp.model.Message;
import uz.pdp.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Store {

    public static List<User> userList = new ArrayList<>(Arrays.asList(
            new User(10, "Aziz", "aziz", "12345"),
            new User(11, "Dilshod", "dilshod", "12345")
    ));

    public static List<Message> messageList = new ArrayList<>();


}
