package service;

import model.Question;
import model.Subject;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import utill.enums.BotState;
import utill.enums.Language;
import utill.enums.Role;
import model.User;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import payload.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BotService extends LanguageService {

    public static UserData getAndCheck(Update update) {
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        User user = UserService.getByChatId(message.getChatId().toString());

        if (user == null) {
            var crt = message.getFrom();
            user = new User(crt.getFirstName(), crt.getLastName(), crt.getUserName(),
                    message.getChatId().toString(), null, Role.USER, Language.RU, BotState.CHOOSE_LANGUAGE);
            UserService.add(user);
        }

        return new UserData(user.getState(), user.getLanguage(), user.getRole());
    }

    public static SendMessage getChooseLanguage(Update update, Language lan) {
        InlineKeyboardMarkup markup = InlineKeyboardService.createMarkup(List.of(
                List.of(UZ, RU, EN)
        ), lan);
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), getWord(CHOOSE_ACTION, lan));
        sendMessage.setReplyToMessageId(message.getMessageId()); // reply qilish uchun
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }

    public static SendMessage getMainMenuSend(Update update, Language lan) {
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();

        if (message.hasContact())
            saveContact(update);

        InlineKeyboardMarkup markup = InlineKeyboardService.createMarkup(List.of(
                List.of(SUBJECTS,MY_RESULTS),
                List.of(SETTINGS)
        ), lan);
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), getWord(CHOOSE_ACTION, lan));
        sendMessage.setReplyToMessageId(message.getMessageId()); // reply qilish uchun
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }

    public static EditMessageText getMainMenuEdit(Update update, Language lan) {
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();

        if (message.hasContact())
            saveContact(update);

        InlineKeyboardMarkup markup = InlineKeyboardService.createMarkup(List.of(
                List.of(SUBJECTS,MY_RESULTS),
                List.of(SETTINGS)
        ), lan);
        EditMessageText editMessage = new EditMessageText(getWord(CHOOSE_ACTION, lan));
        editMessage.setChatId(message.getChatId().toString());
        editMessage.setReplyMarkup(markup);
        editMessage.setMessageId(message.getMessageId());
        return editMessage;
    }

    private static void saveContact(Update update) {
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        User user = UserService.getByChatId(message.getChatId().toString());
        user.setPhoneNumber(message.getContact().getPhoneNumber());
        UserService.add(user);
    }

    public static SendMessage getContactMenu(Update update, Language lan) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        markup.setKeyboard(rowList);
        KeyboardRow row1 = new KeyboardRow();
        rowList.add(row1);
        KeyboardButton button = new KeyboardButton(getWord(MY_CONTACT, lan));
        button.setRequestContact(true);
        row1.add(button);
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();

        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), getWord(CHOOSE_ACTION, lan));
        sendMessage.setReplyMarkup(markup);

        return sendMessage;
    }

    public static DeleteMessage deleteMessage(Update update) {
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        return new DeleteMessage(message.getChatId().toString(), message.getMessageId());
    }

    public static EditMessageText getSettingMenu(Update update, Language lan) {
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        InlineKeyboardMarkup markup = InlineKeyboardService.createMarkup(List.of(
                List.of(CHANGE_LANGUAGE),
                List.of(BACK_TO_MAIN_MENU)
        ), lan);
        EditMessageText editMessage = new EditMessageText(getWord(CHOOSE_ACTION, lan));
        editMessage.setChatId(message.getChatId().toString());
        editMessage.setReplyMarkup(markup);
        editMessage.setMessageId(message.getMessageId());
        return editMessage;
    }

    public static SendMessage removeKeyboard(Update update){
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        SendMessage sendMessage=new SendMessage(message.getChatId().toString(),ARROW);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        return sendMessage;
    }

    public static EditMessageText getChangeLanguage(Update update, Language lan) {
        InlineKeyboardMarkup markup = InlineKeyboardService.createMarkup(List.of(
                List.of(UZ, RU, EN)
        ), lan);
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        EditMessageText editMessageText = new EditMessageText(getWord(CHOOSE_ACTION, lan));
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId()); // reply qilish uchun
        editMessageText.setReplyMarkup(markup);
        return editMessageText;
    }

    public static void saveUserData(UserData userData,Update update) {
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        User user = UserService.getByChatId(message.getChatId().toString());
        user.setRole(userData.getRole());
        user.setLanguage(userData.getLanguage());
        user.setState(userData.getState());
    }


    public static EditMessageText getSubjects(Update update, Language lan) {
        List<Subject> subjectList = SubjectService.getAll();
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        markup.setKeyboard(rowList);
        int ind=0;
        List<InlineKeyboardButton> row=new ArrayList<>();
        for (Subject subject : subjectList) {
            ind++;
            InlineKeyboardButton inlineKeyboardButton=new InlineKeyboardButton(subject.getName());
            inlineKeyboardButton.setCallbackData(subject.getId().toString());
            row.add(inlineKeyboardButton);
            if(ind%3==0){
                rowList.add(row);
                row=new ArrayList<>();
            }
        }
        if(row.size()<3)
            rowList.add(row);
        row=new ArrayList<>();
        InlineKeyboardButton button=new InlineKeyboardButton(getWord(BACK,lan));
        button.setCallbackData(BACK_TO_MAIN_MENU);
        row.add(button);
        rowList.add(row);

        EditMessageText editMessageText=new EditMessageText(getWord(CHOOSE_ACTION,lan));
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setReplyMarkup(markup);
        return editMessageText;
    }

    public static BotState getState(Update update) {
        CallbackQuery query = update.getCallbackQuery();
        UUID id;
        try {
            id=UUID.fromString(query.getData());
            if(SubjectService.checkById(id)){
                return BotState.QUESTION_MENU;
            }
        }catch (Exception e){
            return BotState.MAIN_MENU_SEND;
        }
        return BotState.MAIN_MENU_SEND;
    }

    public static List<SendMessage> getQuestions(Update update, Language lan){
        CallbackQuery query = update.getCallbackQuery();
        List<Question> questions = QuestionService.getAllBySubjectId(UUID.fromString(query.getData()));
        return null;
    } 
}
