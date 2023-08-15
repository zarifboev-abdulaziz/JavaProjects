package uz.pdp.model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Animal {
    private String name;
    private String color;
    private String hair;
    private String tail;
    public static int numberOfLegs = 4;

    public Animal() {
    }

    public Animal(String name) {
        this();
        this.name = name;
    }

    public Animal(String name, String color) {
        this(name);
        this.color = color;
    }

    public Animal(String name, String color, String hair) {
        this(name, color);
        this.hair = hair;
    }

    public void coice () throws IOException {
        return;
    }
}
