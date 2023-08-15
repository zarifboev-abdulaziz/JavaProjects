package uz.pdp;

import uz.pdp.model.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class Main {

    public static void main(String[] args) {

//        String[] names = new String[3];
//
//        names[0] = "Asliddin";
//        names[1] = "Nodirbek";
//        names[2] = "Avaz";
//
//
//        System.out.println(Arrays.toString(names));
//        System.out.println(names.length);
//        String[] names2 = new String[names.length * 2];


        Array<String> nameList = new Array<>(5);

        System.out.println("===Elementlar soni====");
        System.out.println(nameList.size);

        System.out.println("===Yangi element qo'shilyapti====");
        System.out.println(nameList.add("Abror"));
        System.out.println(nameList.add("Eldor"));
        System.out.println(nameList.add("Sardor"));
        System.out.println(nameList.size);
        System.out.println(nameList.add("Avaz"));
        System.out.println(nameList.add("Murtazo"));
        System.out.println(nameList.add("Abdujabbor"));
        System.out.println("Elementlar soni: " + nameList.size);
        System.out.println("Xotiradagi sig'imi: " + nameList.initialCapacity);
        System.out.println(nameList);

        System.out.println(nameList.add("Mirkomil")); // O(n)
        System.out.println("Elementlar soni: " + nameList.size);
        System.out.println("Xotiradagi sig'imi: " + nameList.initialCapacity);

        System.out.println(nameList);

        System.out.println("Removing...");
        System.out.println(nameList.remove(6));
        System.out.println("Elementlar soni: " + nameList.size);
        System.out.println("Xotiradagi sig'imi: " + nameList.initialCapacity);


//        System.out.println("GETTING ELEMENT....");
//        String ism = nameList.elementOlish(5); // O(1)
//        System.out.println(ism);

        System.out.println(nameList);
        System.out.println("ADDING ELEMENT AT SPECIFIED INDEX....");
        System.out.println(nameList.add("Hojiakbar", 6));
        System.out.println(nameList.add("Sevinch", 6));
        System.out.println("Elementlar soni: " + nameList.size);
        System.out.println("Xotiradagi sig'imi: " + nameList.initialCapacity);
//        System.out.println(nameList);


        Array<String> array = new Array("Abror", "Sardor", "Bekzod", "Sevinch", "Abror", "Sardor", "Bekzod", "Sevinch");

        System.out.println(nameList);
        System.out.println(array);

        Array result = nameList.intersect(array);
        System.out.println(result);

        result.reverse();


        System.out.println("==== RESULT LIST ====");
        System.out.println(result);

        System.out.println("==== NAME LIST ====");
        System.out.println(nameList);

        result.addAll(nameList);
        System.out.println(result);

    }
}
