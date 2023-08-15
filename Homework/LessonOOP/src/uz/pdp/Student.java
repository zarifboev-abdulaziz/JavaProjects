package uz.pdp;

public class Student {
    //field
    String firstName;
    String lastName;
    int age;

    //methods
    public Student(){

    }

    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }


    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}' + "\n";
    }



}
