package uz.model;

import uz.service.Live;

public abstract class Person implements Live {
    //Fields
    String name;
    String surname;
    String passport;



    //Methods
    @Override
    public void eat() {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void walk() {

    }
}
