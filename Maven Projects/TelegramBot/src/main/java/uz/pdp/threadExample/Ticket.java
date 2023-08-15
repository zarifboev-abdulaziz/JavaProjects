package uz.pdp.threadExample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {
    private int count = 5;
    private String userName;

    public boolean makeOrder(int inputCount){
        if (count > inputCount){
            count -= inputCount;
            return true;
        }else {
            return false;
        }
    }
}
