package service;

import model.Answer;
import model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnswerService {
    private static final List<Answer> answerList=new ArrayList<>();

    public static void getInitialData(){
        int ind=0;
        for (Question question : QuestionService.getAll()) {
            answerList.add(new Answer(question.getId(),ind++%4==0,ind%4==0?"TRUE1":"FALSE1"));
            answerList.add(new Answer(question.getId(),ind++%4==0,ind%4==0?"TRUE2":"FALSE2"));
            answerList.add(new Answer(question.getId(),ind++%4==0,ind%4==0?"TRUE3":"FALSE3"));
            answerList.add(new Answer(question.getId(),ind++%4==0,ind%4==0?"TRUE4":"FALSE4"));
        }
    }

    public static   List<Answer> getAll() {
        return answerList;
    }

    public static Answer getById(UUID id) {
        return null;
    }

    public static Answer add(Answer answer) {
        return null;
    }

    public static Answer delete(UUID id) {
        return null;
    }

    public static boolean checkById(UUID id) {
        return false;
    }

    public static List<Answer> getAllByQuestionId(UUID questionId){
        List<Answer> res=new ArrayList<>();
        for (Answer answer : answerList)
            if(answer.getQuestionId().equals(questionId))
                res.add(answer);
        return res;
    }
}
