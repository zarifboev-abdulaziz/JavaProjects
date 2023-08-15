package uz.pdp.utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.model.User;
import uz.pdp.model.enums.State;
import uz.pdp.model.jsonPlaceHolder.Journalist;
import uz.pdp.model.jsonPlaceHolder.Post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class DataBase {


    public static List<User> userList = new ArrayList<>();

    public static List<Post> postList = new ArrayList<>();

    public static List<Journalist> journalistList = new ArrayList<>();

    public static void getDataFromPlaceHolder(){
        try {
            Gson gson = new Gson();
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            List<Post> postListFromJson = gson.fromJson(bufferedReader, new TypeToken<List<Post>>(){}.getType());

            postList.addAll(postListFromJson);
            System.out.println("PostList is ready!!!");


            URL url1 = new URL("https://jsonplaceholder.typicode.com/users");
            URLConnection urlConnection1 = url1.openConnection();
            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(urlConnection1.getInputStream()));

            List<Journalist> journalistListFromJson = gson.fromJson(bufferedReader1, new TypeToken<List<Journalist>>(){}.getType());

            journalistList.addAll(journalistListFromJson);
            System.out.println("Journalist list is ready!!!");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static User getUserFromList(Update update){

        if(update.hasMessage()){
            for (User user : userList) {
                if (user.getChatId().equals(update.getMessage().getChatId().toString())){
                    return user;
                }
            }
        } else if (update.hasCallbackQuery()){
            for (User user : userList) {
                if (user.getChatId().equals(update.getCallbackQuery().getMessage().getChatId().toString())){
                    return user;
                }
            }
        }


        User newUser = new User();
        newUser.setFirstName(update.getMessage().getFrom().getFirstName());
        newUser.setLastName(update.getMessage().getFrom().getLastName());
        newUser.setUsername(update.getMessage().getFrom().getUserName());
        newUser.setChatId(update.getMessage().getChatId().toString());
        newUser.setState(State.MAIN_MENU);


        userList.add(newUser);
        return newUser;
    }

}
