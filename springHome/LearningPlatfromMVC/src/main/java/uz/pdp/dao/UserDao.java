package uz.pdp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.model.User;

@Component
public class UserDao {
    @Autowired
    JdbcTemplate template;

    public User getUserById(Integer userId) {
        String query = "select * from users where id = " + userId;

        User user = template.queryForObject(query, (resultSet, rowNum) -> {
            User userFromDb = new User();

            userFromDb.setId(resultSet.getInt(1));
            userFromDb.setFirstName(resultSet.getString(2));
            userFromDb.setLastName(resultSet.getString(3));
            userFromDb.setEmail(resultSet.getString(4));
            userFromDb.setPassword(resultSet.getString(5));
            userFromDb.setRoleId(resultSet.getInt(6));
            userFromDb.setBalance(resultSet.getDouble(7));

            return userFromDb;
        });
        return user;
    }

    public void editUserInfo(User user) {
        String sqlForBalance =
                "Update users set balance = "+user.getBalance()+" where id = " + user.getId();

        if (user.getBalance() != null){
            template.update(sqlForBalance);
        }

        String sqlForPassword = "Update users set password = "+user.getPassword()+" where id = " + user.getId();

        if (user.getPassword() != null){
            template.update(sqlForPassword);
        }

        String sql = "Update users set first_name = '"+user.getFirstName()+"', last_name = '"+user.getLastName() +
                        "', email " +
                        "= '"+user.getEmail()+
                        "' where id = " + user.getId();

        template.update(sql);
    }
}
