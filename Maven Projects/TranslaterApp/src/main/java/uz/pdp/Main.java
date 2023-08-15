package uz.pdp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.pdp.model.Def;
import uz.pdp.model.DicResult;
import uz.pdp.model.Tr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String apiKey = "dict.1.1.20211211T055537Z.ba9cd28e187c76d4.fb04b2db1b4da7046b73168e732f5ee39f307f62";

//        URL url = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/getLangs?key=" + apiKey);
//        URLConnection urlConnection = url.openConnection();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//        String line;
//        while ((line = bufferedReader.readLine()) != null){
//            System.out.println(line);
//        }

        String language = "";
        String text = "";

        System.out.println("Welcome to translator app!");
        System.out.println("Select one of the options!");
        System.out.println("1=> English to Russian\n2=> English to France\n3=> Russian to English\n4=> Russian to France\n5=> Exit");
        int option = new Scanner(System.in).nextInt();
        switch (option){
            case 1:
                System.out.println("Enter text: ");
                text = new Scanner(System.in).nextLine();
                language = "en-ru";
                break;
            case 2:
                System.out.println("Enter text: ");
                text = new Scanner(System.in).nextLine();
                language = "en-fr";
                break;
            case 3:
                System.out.println("Enter text: ");
                text = new Scanner(System.in).nextLine();
                language = "ru-en";
                break;
            case 4:
                System.out.println("Enter text: ");
                text = new Scanner(System.in).nextLine();
                language = "ru-fr";
                break;
            case 5:return;
            default:
                System.out.println("Wrong option!!!");
                break;
        }

        System.out.println("Translation: " + lookUp(apiKey, text, language));

        main(args);

    }

    public static String lookUp(String key, String text, String lang) throws IOException {
        String result = "";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        URL url = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=" + key + "&lang="+ lang +"&text="+text);

        URLConnection connection = url.openConnection();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader( connection.getInputStream()));

        DicResult dicResult = gson.fromJson(bufferedReader, DicResult.class);

        if (dicResult.getDef().size() != 0){

            for (Def def : dicResult.getDef()) {
                for (Tr tr : def.getTr()) {
                    result += tr.getText() + ", ";
                }
            }
        }else {
            System.out.println("Word not found!!!");
        }
        return result;
    }

}
