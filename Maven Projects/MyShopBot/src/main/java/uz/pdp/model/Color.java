package uz.pdp.model;

import uz.pdp.model.abs.AbsEntity;

public class Color extends AbsEntity {
    private String name;

    public Color() {
    }

    public Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}
