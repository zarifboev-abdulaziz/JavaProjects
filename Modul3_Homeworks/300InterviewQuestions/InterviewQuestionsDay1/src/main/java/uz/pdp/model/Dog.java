package uz.pdp.model;

import uz.pdp.model.Animal;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Dog extends Animal {
    String legColor;

    public Dog() {
        System.out.println("Dog Constructor Chaqirildi");
    }

    public Dog(String legColor) {
        super();
    }

    public void bark(){
        System.out.println("Dog is barking");
    }


}
