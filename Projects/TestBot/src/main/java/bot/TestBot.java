package bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import payload.UserData;
import service.BotService;
import utill.Constant;
import utill.baseData.BaseData;
import utill.enums.BotState;
import utill.enums.Language;
import utill.enums.Role;

import java.util.List;

public class TestBot extends TelegramLongPollingBot implements Constant {


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        UserData data = BotService.getAndCheck(update);
        BotState state = data.getState();
        Language lan = data.getLanguage();
        Role role = data.getRole();


        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals(START)) {
                    if (!state.equals(BotState.CHOOSE_LANGUAGE)) {
                        state = BotState.MAIN_MENU_SEND;
                    }
                }
            } else if (message.hasContact()) {
                state = BotState.MAIN_MENU_SEND;
            }
        } else if (update.hasCallbackQuery()) {
            switch (update.getCallbackQuery().getData()) {
                case UZ -> {
                    lan = Language.UZ;
                    if (state.equals(BotState.CHOOSE_LANGUAGE))
                        state = BotState.SHARE_CONTACT;
                    else
                        state = BotState.SETTINGS_MENU;
                }
                case RU -> {
                    lan = Language.RU;
                    if (state.equals(BotState.CHOOSE_LANGUAGE))
                        state = BotState.SHARE_CONTACT;
                    else
                        state = BotState.SETTINGS_MENU;
                }
                case EN -> {
                    lan = Language.EN;
                    if (state.equals(BotState.CHOOSE_LANGUAGE))
                        state = BotState.SHARE_CONTACT;
                    else
                        state = BotState.SETTINGS_MENU;
                }
                case SETTINGS -> state = BotState.SETTINGS_MENU;
                case BACK_TO_MAIN_MENU -> state = BotState.MAIN_MENU_EDIT;
                case CHANGE_LANGUAGE -> state = BotState.CHANGE_LANGUAGE;
                case SUBJECTS -> state=BotState.SUBJECT_MENU;
                default -> state=BotService.getState(update);
            }
        }

        switch (state) {
            case CHOOSE_LANGUAGE -> execute(BotService.getChooseLanguage(update, lan));
            case SHARE_CONTACT -> {
                execute(BotService.deleteMessage(update));
                execute(BotService.getContactMenu(update, lan));
            }
            case MAIN_MENU_SEND -> {
                execute(BotService.removeKeyboard(update));
                execute(BotService.getMainMenuSend(update, lan));
            }
            case MAIN_MENU_EDIT -> execute(BotService.getMainMenuEdit(update, lan));
            case SETTINGS_MENU -> execute(BotService.getSettingMenu(update, lan));
            case CHANGE_LANGUAGE -> execute(BotService.getChangeLanguage(update, lan));
            case SUBJECT_MENU -> execute(BotService.getSubjects(update,lan));
            case QUESTION_MENU -> {
                List<SendMessage> ques=BotService.getQuestions(update,lan);
                for (SendMessage que : ques)
                    execute(que);
            }
        }
        BotService.saveUserData(new UserData(state,lan,role),update);
    }


    @Override
    public String getBotUsername() {
        return BaseData.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BaseData.BOT_TOKEN;
    }
}
