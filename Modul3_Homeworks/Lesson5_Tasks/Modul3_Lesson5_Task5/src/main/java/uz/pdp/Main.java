package uz.pdp;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Enter pathName: ");
        String pathName = new Scanner(System.in).nextLine();

        try {
        findLongestWord(pathName);

        }catch (FileNotFoundException e){
            System.err.println("File Not Found");
        }



    }

    public static void findLongestWord(String pathName) throws FileNotFoundException{

        File file = new File(pathName);
        try (Reader reader = new FileReader(file)) {

            char[] chars = new char[(int)file.length()];

            reader.read(chars);

            int maxLetter = 0;
            String longestWord = "";
            int tempLetter = 0;
            String tempWord = "";

            for (int i = 0; i < chars.length; i++) {

                if (Character.isLetter(chars[i])){
                    for (int j = i; j < chars.length && Character.isLetter(chars[j]); j++) {
                        tempLetter++;
                        tempWord += "" + chars[j];
                    }

                    if (tempLetter > maxLetter){
                        maxLetter = tempLetter;
                        longestWord = tempWord;
                    }
                    tempLetter = 0;
                    tempWord = "";
                }
            }

            System.out.println("Longest word: " + longestWord);
            System.out.println("Number of letters: " + maxLetter);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
