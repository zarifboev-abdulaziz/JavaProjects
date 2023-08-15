package service;

import model.Question;
import model.Subject;
import utill.enums.SubjectEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionService {
    private static final List<Question> questionList = new ArrayList<>();

    public static List<Question> getAll() {
        return questionList;
    }

    public static void getInitialData() {
        for (Subject subject : SubjectService.getAll()) {
            questionList.add(new Question(subject.getId(), "Bir nima1", 5));
            questionList.add(new Question(subject.getId(), "Bir nima2", 10));
        }
    }

    public static Question getById(UUID id) {
        return null;
    }

    public static Question add(Question question) {
        return null;
    }

    public static Question delete(UUID id) {
        return null;
    }

    public static boolean checkById(UUID id) {
        return false;
    }

    public static List<Question> getAllBySubjectId(UUID subjectId){
        List<Question> res=new ArrayList<>();
        for (Question question : questionList)
            if(question.getSubjectId().equals(subjectId))
                res.add(question);
        return res;
    }
 }
