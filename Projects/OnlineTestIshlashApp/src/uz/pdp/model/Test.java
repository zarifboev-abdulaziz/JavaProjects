package uz.pdp.model;

public class Test {
    //Fields
    private int id;
    private String question;
    private String belongedSubject;
    private float scorePerTest;
    private int trueAnswerID;

    public Test() {
    }

    public Test(int id, String question, String belongedSubject, float scorePerTest, int trueAnswerID) {
        this.id = id;
        this.question = question;
        this.belongedSubject = belongedSubject;
        this.scorePerTest = scorePerTest;
        this.trueAnswerID = trueAnswerID;
    }

    public int getTrueAnswerID() {
        return trueAnswerID;
    }

    public void setTrueAnswerID(int trueAnswerID) {
        this.trueAnswerID = trueAnswerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getBelongedSubject() {
        return belongedSubject;
    }

    public void setBelongedSubject(String belongedSubject) {
        this.belongedSubject = belongedSubject;
    }

    public float getScorePerTest() {
        return scorePerTest;
    }

    public void setScorePerTest(float scorePerTest) {
        this.scorePerTest = scorePerTest;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", belongedSubject='" + belongedSubject + '\'' +
                ", scorePerTest=" + scorePerTest +
                '}';
    }
}
