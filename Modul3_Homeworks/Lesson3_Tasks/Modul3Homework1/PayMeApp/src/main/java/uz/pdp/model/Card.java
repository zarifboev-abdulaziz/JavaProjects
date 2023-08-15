package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.enums.CardType;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Card {
    private int id;
    private User owner;
    private double balance;
    private CardType cardType;

}
