package service;

import model.Subject;
import utill.enums.SubjectEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubjectService {
    private static final List<Subject> subjectList=new ArrayList<>();

    public static void getInitialData(){
        for (SubjectEnum value : SubjectEnum.values()) {
            subjectList.add(new Subject(value.name()));
        }
    }

    public static   List<Subject> getAll() {
        return subjectList;
    }

    public static Subject getById(UUID id) {
        return null;
    }

    public static Subject add(Subject subject) {
        return null;
    }

    public static Subject delete(UUID id) {
        return null;
    }

    public static boolean checkById(UUID id) {
        for (Subject subject : subjectList)
            if(subject.getId().equals(id))
                return true;
        return false;
    }
}
