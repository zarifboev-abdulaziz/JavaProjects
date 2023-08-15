package uz.pdp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;
import uz.pdp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthDao {
    @Autowired
    JdbcTemplate template;

    public Integer registerUserToDb(User user) {
        String sql = "Insert into users (first_name, last_name, email, password) Values (?, ?, " +
                "?, ?) returning id";
        Integer userId = null;
        try {
            userId = template.execute(sql, new PreparedStatementCallback<Integer>() {
                @Override
                public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException,
                        DataAccessException {

                    ps.setString(1, user.getFirstName());
                    ps.setString(2, user.getLastName());
                    ps.setString(3, user.getEmail());
                    ps.setString(4, user.getPassword());

                    ResultSet resultSet = ps.executeQuery();
                    if (resultSet.next()){
                       return resultSet.getInt(1);
                    }
                    return null;
                }
            });

        } catch (Exception e){
            return null;
        }
        return userId;
    }

    public User loginUserToDb(User inputUser) {
        String sql = "Select id, role_id from users where email = ? AND password = ?";
        User loginUser = null;
        try {
            loginUser = template.execute(sql, new PreparedStatementCallback<User>() {
                @Override
                public User doInPreparedStatement(PreparedStatement ps) throws SQLException,
                        DataAccessException {

                    ps.setString(1, inputUser.getEmail());
                    ps.setString(2, inputUser.getPassword());

                    ResultSet resultSet = ps.executeQuery();
                    User user = new User();
                    if (resultSet.next()){
                        user.setId(resultSet.getInt(1));
                        user.setRoleId(resultSet.getInt(2));
                    }
                    return user;
                }
            });

        } catch (Exception e){
            return null;
        }
        return loginUser;
    }
}
