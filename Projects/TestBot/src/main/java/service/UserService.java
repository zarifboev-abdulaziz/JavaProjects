package service;


import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService  {
    private static final List<User> userList=new ArrayList<>();
    public static   List<User> getAll() {
        return userList;
    }

    public static User getById(UUID id) {
        return null;
    }

    public static User add(User user) {
        boolean isExist=false;
        for (User item : userList)
            if(item.getId().equals(user.getId())){
                isExist=true;
                break;
            }
        if(!isExist)
            userList.add(user);
        return user;
    }

    public static User delete(UUID id) {
        return null;
    }

    public static boolean checkById(UUID id) {
        return false;
    }

    public static User getByChatId(String chatId) {
        for (User user : userList)
            if(user.getChatId().equals(chatId))
                return user;
        return null;
    }


}
