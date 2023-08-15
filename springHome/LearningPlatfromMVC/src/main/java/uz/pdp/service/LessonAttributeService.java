package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.LessonAttributeDao;
import uz.pdp.dto.CommentDto;

import java.util.List;

@Service
public class LessonAttributeService {
    @Autowired
    LessonAttributeDao lessonAttributeDao;

    public void likeButtonPressed(Integer lessonId, Integer userId) {
        lessonAttributeDao.likeButtonControl(lessonId, userId);
    }

    public Integer getNumberOfLikes(Integer lessonId) {
        return lessonAttributeDao.getNumberOfLikesFromDb(lessonId);
    }

    public void addCommentToLesson(CommentDto commentDto) {
        lessonAttributeDao.addCommentToLessonDb(commentDto);
    }

    public List<CommentDto> getAllComments(Integer lessonId) {
        return lessonAttributeDao.getAllCommentsFromDb(lessonId);
    }
}
