package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Passport {
    private int id;
    private String firstname;
    private String surname;
    private String serialNumber;
    private String contactNumber;
    private State state;
    private String chatId;

    @Override
    public String toString() {
        return "Firstname: " + firstname  +
                ", Surname: " + surname +
                ", Passport Number: " + serialNumber +
                ", Contact Number: " + contactNumber +
                ", State" + state.toString();
    }
}
