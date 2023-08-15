package uz.pdp.model;

public class UserTestSolveHistory {
    private int id;
    private String subjectName;
    private float maxBall;
    private float abiturientScore;

    public UserTestSolveHistory() {
    }

    public UserTestSolveHistory(int id, String subjectName, float maxBall, float abiturientScore) {
        this.id = id;
        this.subjectName = subjectName;
        this.maxBall = maxBall;
        this.abiturientScore = abiturientScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public float getMaxBall() {
        return maxBall;
    }

    public void setMaxBall(float maxBall) {
        this.maxBall = maxBall;
    }

    public float getAbiturientScore() {
        return abiturientScore;
    }

    public void setAbiturientScore(float abiturientScore) {
        this.abiturientScore = abiturientScore;
    }

    @Override
    public String toString() {
        return "UserTestSolveHistory{" +
                "id=" + id +
                ", subjectName=" + subjectName +
                ", maxBall=" + maxBall +
                ", abiturientScore=" + abiturientScore +
                '}';
    }
}
