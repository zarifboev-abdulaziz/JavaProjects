package uz.pdp;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Map<String, String> dictionary = new HashMap<>();

        File file = new File("src/main/resources/oxford.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            String line = bufferedReader.readLine();

            while (line != null){
                String word = "";

                if (line.length() > 1){

                    for (int i = 0; (line.charAt(i) != ' '); i++) {
                        word += "" + line.charAt(i);
                    }
                    dictionary.put(word, line);
                }

                word = "";
                line = bufferedReader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Enter word from oxford dictionary to search the meaning: ");
        String searchWord = new Scanner(System.in).nextLine();

        boolean isFound = false;

        for (String eachWord : dictionary.keySet()) {
            if (eachWord.equalsIgnoreCase(searchWord)){
                isFound = true;
                System.out.println(dictionary.get(searchWord));
                break;
            }
        }

        if (!isFound){
            System.out.println("Word not found!!!");
        }


    }
}
