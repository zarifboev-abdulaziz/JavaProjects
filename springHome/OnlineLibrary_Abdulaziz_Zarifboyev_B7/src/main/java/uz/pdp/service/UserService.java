package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.UserDao;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

}
