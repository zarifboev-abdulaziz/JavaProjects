package uz.pdp.model;

public class UserTestSolveHistory {
    private int id;
    private String subjectName;
    private double maxBall;
    private double abiturientBall;

    public UserTestSolveHistory() {
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

    public double getMaxBall() {
        return maxBall;
    }

    public void setMaxBall(double maxBall) {
        this.maxBall = maxBall;
    }

    public double getAbiturientBall() {
        return abiturientBall;
    }

    public void setAbiturientBall(double abiturientBall) {
        this.abiturientBall = abiturientBall;
    }

    @Override
    public String toString() {
        return "UserTestSolveHistory{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", maxBall=" + maxBall +
                ", abiturientBall=" + abiturientBall +
                '}';
    }
}
