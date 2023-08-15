package uz.pdp;

import uz.pdp.model.Card;
import uz.pdp.model.User;
import uz.pdp.model.enums.CardType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {

    public static List<User> userList = new ArrayList<>(Arrays.asList(
            new User(1, "Aziz", "12345", "qwerty"),
            new User(2, "Ismoil", "54321", "qwerty")

    ));

    public static List<Card> cardList = new ArrayList<>(Arrays.asList(
            new Card(11, new User(1, "Aziz", "12345", "qwerty"), 1000, CardType.HUMO),
            new Card(12, new User(2, "Ismoil", "54321", "qwerty"), 500, CardType.HUMO)
    ));

}
