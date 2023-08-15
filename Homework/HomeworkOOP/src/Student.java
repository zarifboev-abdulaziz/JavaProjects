import java.util.Arrays;

public class Student {
    String lastName;
    String firstName;
    String groupNumber;
    String[] subjects = {"Matematika", "Fizika", "O'qish", "Biologiya", "Ona yili"};

    void printSubjects(){
        System.out.println(Arrays.toString(subjects));
    }

}
