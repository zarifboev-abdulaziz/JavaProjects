package uz.pdp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class UserActivity {
    private int round = 0;
    private String category;
    private String selectedBook;
    private String selectedPayType;
}
