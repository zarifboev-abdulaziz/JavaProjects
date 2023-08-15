package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.UserDao;
import uz.pdp.model.User;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getUserInfoById(Integer studentId) {
        return userDao.getUserById(studentId);
    }

    public void editUserInfo(User user) {
        userDao.editUserInfo(user);
    }
}
