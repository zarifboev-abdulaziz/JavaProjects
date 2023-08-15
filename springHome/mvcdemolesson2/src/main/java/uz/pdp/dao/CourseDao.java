package uz.pdp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.dto.CourseDto;
import uz.pdp.model.Author;

import java.util.List;

@Component
public class CourseDao {
    @Autowired
    JdbcTemplate template;

    public List<CourseDto> getCourses(Integer page){
        String query =
                "Select id, name, is_active, description from courses limit 5 offset " + ((page-1) * 5);

        List<CourseDto> list = template.query(query, (resultSet, rowNum) -> {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(resultSet.getInt("id"));
            courseDto.setName(resultSet.getString("name"));
            courseDto.setActive(resultSet.getBoolean("is_active"));
            courseDto.setDescription(resultSet.getString("description"));

            return courseDto;
        });

        return list;
    }

    public int saveCourseToDb(CourseDto courseDto, List<Integer> authorIds) {
        Integer id = courseDto.getId();
        String name = courseDto.getName();
        String description = courseDto.getDescription();

        if (id != null){
            return template.update("UPDATE courses set name = '"+name+"', description = '"+description+"' where id = " + id);

        }

        template.update("INSERT INTO courses (name, description) VALUES ('"+name+"', '"+description+ "')");

        String sql = "Select max(id) from courses";

        Integer lastCourseId = template.queryForObject(sql, (rs, rowNum) -> {
            Integer e = rs.getInt(1);
            return e;
        });

        for (Integer authorId : authorIds) {
            template.update("INSERT INTO courses_authors (course_id, author_id) values ( "+lastCourseId+", "+authorId+")");
        }

        return 0;
    }

    public CourseDto getCourseByIdFromDb(Integer id) {
        CourseDto courseDto = null;
        String sql = "Select id, name, description from courses where id= ?";
        try {
            courseDto = template.queryForObject(sql, new Object[]{id},
                    new BeanPropertyRowMapper<CourseDto>(CourseDto.class));
        } catch (Exception e){
            e.printStackTrace();
        }

        String query = "select ca.course_id, ca.author_id, a.full_name\n" +
                "from courses_authors ca\n" +
                "join authors a on a.id = ca.author_id\n" +
                "where course_id = "+id;

        List<Author> authors = template.query(query, (resultSet, rowNum) -> {
            Author author = new Author();
            author.setId(resultSet.getInt(2));
            author.setFullName(resultSet.getString(3));
            return author;
        });

        courseDto.setAuthors(authors);
        return courseDto;
    }

    public int deleteCourseByIdFromDb(Integer id) {
        String query = "DELETE FROM courses_authors where course_id = "+id;
        template.update(query);

        String sql = "DELETE FROM courses where id = " + id;
        return template.update(sql);
    }

    public Author getAuthorByIdFromDb(Integer id) {
        Author author = null;
        String sql = "Select id, full_name, user_name, password, email from authors where id= ?";
        try {
            author = template.queryForObject(sql, new Object[]{id},
                    new BeanPropertyRowMapper<Author>(Author.class));
        } catch (Exception e){
            e.printStackTrace();
        }
        return author;
    }

    public List<CourseDto> getAuthorCoursesFromDb(Integer authorId) {

        String query  ="select c.id, c.name\n" +
                "from courses_authors ca\n" +
                "join courses c on c.id = ca.course_id\n" +
                "where ca.author_id = "+ authorId;

        List<CourseDto> list = template.query(query, (resultSet, rowNum) -> {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(resultSet.getInt(1));
            courseDto.setName(resultSet.getString(2));
            return courseDto;
        });
        return list;
    }

    public List<Author> getAllAuthorsFromDb() {
        String query  ="Select id, full_name, user_name, email, password from authors";

        List<Author> list = template.query(query, (resultSet, rowNum) -> {
            Author author = new Author();
            author.setId(resultSet.getInt(1));
            author.setFullName(resultSet.getString(2));
            author.setUserName(resultSet.getString(3));
            author.setEmail(resultSet.getString(4));
            author.setPassword(resultSet.getString(5));

            return author;
        });
        return list;
    }

    public List<CourseDto> getCoursesBySearch(String search) {
        search = search.toUpperCase();
        String query =
                "Select id, name, is_active, description from courses where upper(name) " +
                        "like '%"+search+"%' ";

        List<CourseDto> list = template.query(query, (resultSet, rowNum) -> {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(resultSet.getInt("id"));
            courseDto.setName(resultSet.getString("name"));
            courseDto.setActive(resultSet.getBoolean("is_active"));
            courseDto.setDescription(resultSet.getString("description"));

            return courseDto;
        });

        return list;
    }

    public int getTotalCourse() {
        String query = "select count(*) from courses";

        return template.queryForObject(query, (rs, rowNum) -> rs.getInt(1));
    }
}
