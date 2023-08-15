package service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import utill.enums.Language;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardService extends LanguageService{

    public static InlineKeyboardMarkup createMarkup(List<List<String>> rows, Language lan){
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        markup.setKeyboard(rowList);

        for (List<String> buttons : rows) {
            List<InlineKeyboardButton> row=new ArrayList<>();
            rowList.add(row);
            for (String button : buttons) {
                InlineKeyboardButton keyboardButton;
               if(button.contains("BACK")){
                   keyboardButton=new InlineKeyboardButton(getWord(BACK,lan));
                   keyboardButton.setCallbackData(button);
               }else {
                   keyboardButton=new InlineKeyboardButton(getWord(button,lan));
                   keyboardButton.setCallbackData(button);
               }
                row.add(keyboardButton);
            }
        }

        return markup;
    }
}
