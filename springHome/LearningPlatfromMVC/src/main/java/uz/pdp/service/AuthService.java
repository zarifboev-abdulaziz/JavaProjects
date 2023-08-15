package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.AuthDao;
import uz.pdp.model.User;

@Service
public class AuthService {
    @Autowired
    AuthDao authDao;

    public Integer registerUser(User user) {
        return authDao.registerUserToDb(user);
    }

    public User loginUser(User user) {
        return authDao.loginUserToDb(user);
    }
}
