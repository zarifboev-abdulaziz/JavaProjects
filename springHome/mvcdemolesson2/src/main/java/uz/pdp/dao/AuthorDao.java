package uz.pdp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.dto.CourseDto;
import uz.pdp.model.Author;

import java.util.List;

@Component
public class AuthorDao {
    @Autowired
    JdbcTemplate template;

    public List<Author> getAuthors() {
        String query  ="Select * from authors";

        List<Author> list = template.query(query, (resultSet, rowNum) -> {
            Author author = new Author();
            author.setId(resultSet.getInt(1));
            author.setFullName(resultSet.getString(2));
            author.setUserName(resultSet.getString(3));
            author.setPassword(resultSet.getString(4));
            author.setEmail(resultSet.getString(5));

            return author;
        });
        return list;

    }

    public void saveAuthorToDb(Author author) {
        Integer id = author.getId();
        String fullName = author.getFullName();
        String email = author.getEmail();
        String userName = author.getUserName();
        String password = author.getPassword();

        if (id != null){
             template.update("UPDATE authors set full_name = '"+fullName+"', user_name =" +
                    " '"+userName+"', password = '"+password+"', email = '"+email+"' where id =" +id);
             return;
        }

        template.update("INSERT INTO authors (full_name, user_name, password, email)" +
                " VALUES ('"+fullName+"', '"+userName+ "', '"+password+"', '"+email+"')");

    }

    public void deleteAuthorByIdFromDb(Integer id) {
        String query = "DELETE FROM courses_authors where author_id = "+id;
        template.update(query);

        String sql = "DELETE FROM authors where id = " + id;
        template.update(sql);
    }
}
