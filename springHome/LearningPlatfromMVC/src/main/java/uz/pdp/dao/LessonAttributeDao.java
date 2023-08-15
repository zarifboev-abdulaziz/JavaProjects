package uz.pdp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.dto.CommentDto;

import java.util.List;

@Component
public class LessonAttributeDao {
    @Autowired
    JdbcTemplate template;

    public void likeButtonControl(Integer lessonId, Integer userId) {
        String query = "Select * from like_button_increment("+userId+", "+lessonId+")";
        template.queryForObject(query, (rs, rowNum) -> rs.getBoolean(1));

    }

    public Integer getNumberOfLikesFromDb(Integer lessonId) {
        String query = "Select count(*) from likes where lesson_id = " + lessonId;

        return template.queryForObject(query, (rs, rowNum) -> rs.getInt(1));
    }

    public void addCommentToLessonDb(CommentDto commentDto) {
        String sql = "INSERT INTO comments (lesson_id, user_id, body) VALUES ("+commentDto.getLessonId()+", " +
                " "+commentDto.getUserId()+" , '"+commentDto.getBody()+"')";

        template.update(sql);

    }

    public List<CommentDto> getAllCommentsFromDb(Integer lessonId) {
        String queryForComments = "Select * from comments where lesson_id = " + lessonId +
                " order by created_at desc";

        List<CommentDto> commentDtoList= template.query(queryForComments, (rs, rowNum) -> {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(rs.getInt(1));
            commentDto.setLessonId(rs.getInt(2));
            commentDto.setUserId(rs.getInt(3));
            commentDto.setBody(rs.getString(4));

            return commentDto;
        });

        for (CommentDto commentDto : commentDtoList) {
            String queryForUserName =
                    "Select first_name, last_name from users where id = " + commentDto.getUserId();

            String fullName = template.queryForObject(queryForUserName, (rs, rowNum) -> {
                String fullNameOfUser = "";
                fullNameOfUser += rs.getString(1) + " ";
                fullNameOfUser += rs.getString(2);
                return fullNameOfUser;
            });

            commentDto.setUserName(fullName);
        }

        return commentDtoList;
    }
}
