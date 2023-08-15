package service;

import model.User;
import model.UserQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserQuestionService {
    private static final List<UserQuestion> userQuestionList = new ArrayList<>();

    public static List<UserQuestion> getAll() {
        return userQuestionList;
    }

    public static UserQuestion getById(UUID id) {
        return null;
    }

    public static UserQuestion add(UserQuestion userQuestion) {
        return null;
    }

    public static UserQuestion delete(UUID id) {
        return null;
    }

    public static boolean checkById(UUID id) {
        return false;
    }
}
