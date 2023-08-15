package service;

import utill.Constant;
import utill.enums.Language;

public class LanguageService implements Constant {
    public static String getWord(String word, Language lan) {
       return switch (lan) {
            case UZ -> getUzWord(word);
            case RU -> getRuWord(word);
            case EN -> word;

        };
    }

    private static String getUzWord(String word){
        return switch (word){
            case UZ -> UZ_UZ;
            case RU -> RU_UZ;
            case EN -> EN_UZ;
            case CHOOSE_ACTION -> CHOOSE_ACTION_UZ;
            case MY_CONTACT -> MY_CONTACT_UZ;
            case BACK ->  BACK_UZ;
            case SUBJECTS -> SUBJECTS_UZ;
            case SETTINGS -> SETTINGS_UZ;
            case MY_RESULTS -> MY_RESULTS_UZ;
            case CHANGE_LANGUAGE -> CHANGE_LANGUAGE_UZ;
            default -> ERROR;
        };
    }
    private static String getRuWord(String word){
        return switch (word){
            case UZ -> UZ_RU;
            case RU -> RU_RU;
            case EN -> EN_RU;
            case CHOOSE_ACTION -> CHOOSE_ACTION_RU;
            case MY_CONTACT -> MY_CONTACT_RU;
            case BACK ->  BACK_RU;
            case SUBJECTS -> SUBJECTS_RU;
            case SETTINGS -> SETTINGS_RU;
            case MY_RESULTS -> MY_RESULTS_RU;
            case CHANGE_LANGUAGE -> CHANGE_LANGUAGE_RU;
            default -> ERROR;
        };
    }
}
