package uz.pdp.model;

import uz.pdp.model.enums.State;

public class Student extends User{
    private State state = State.MAIN_MENU;
    private Subject selectedSubject;
    private int currentTestNumber = 0;
    private Integer currentMessageId;
    private int correctAnswers = 0;


    public Student() {
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Subject getSelectedSubject() {
        return selectedSubject;
    }

    public void setSelectedSubject(Subject selectedSubject) {
        this.selectedSubject = selectedSubject;
    }

    public int getCurrentTestNumber() {
        return currentTestNumber;
    }

    public void setCurrentTestNumber(int currentTestNumber) {
        this.currentTestNumber = currentTestNumber;
    }

    public Integer getCurrentMessageId() {
        return currentMessageId;
    }

    public void setCurrentMessageId(Integer currentMessageId) {
        this.currentMessageId = currentMessageId;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }


}
