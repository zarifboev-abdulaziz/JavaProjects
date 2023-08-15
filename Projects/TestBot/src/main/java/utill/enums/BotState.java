package utill.enums;

import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public enum BotState {
    CHOOSE_LANGUAGE,
    SHARE_CONTACT,
    MAIN_MENU_SEND,
    MAIN_MENU_EDIT,
    CHANGE_LANGUAGE,
    SETTINGS_MENU,
    SUBJECT_MENU,
    QUESTION_MENU;
}
