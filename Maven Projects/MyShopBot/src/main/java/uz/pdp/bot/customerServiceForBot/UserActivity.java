package uz.pdp.bot.customerServiceForBot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.PayType;
import uz.pdp.model.abs.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserActivity {
    private User user;
    private Integer quantityMsgId;
    private int currentClothQuantity = 1;
    private PayType payType;

}
