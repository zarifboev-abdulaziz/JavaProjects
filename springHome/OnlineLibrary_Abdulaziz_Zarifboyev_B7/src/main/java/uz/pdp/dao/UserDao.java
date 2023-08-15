package uz.pdp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    @Autowired
    public SessionFactory sessionFactory;


}
