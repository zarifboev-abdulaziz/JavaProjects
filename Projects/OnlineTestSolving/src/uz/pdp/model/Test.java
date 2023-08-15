package uz.pdp.model;

public class Test {
   private int id;
   private String questionBody;
   private String belongedSubject;
   private int trueAnswerId;

    public Test() {
    }

    public Test(int id, String questionBody, String belongedSubject, int trueAnswerId) {
        this.id = id;
        this.questionBody = questionBody;
        this.belongedSubject = belongedSubject;
        this.trueAnswerId = trueAnswerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public String getBelongedSubject() {
        return belongedSubject;
    }

    public void setBelongedSubject(String belongedSubject) {
        this.belongedSubject = belongedSubject;
    }

    public int getTrueAnswerId() {
        return trueAnswerId;
    }

    public void setTrueAnswerId(int trueAnswerId) {
        this.trueAnswerId = trueAnswerId;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", questionBody='" + questionBody + '\'' +
                ", belongedSubject='" + belongedSubject + '\'' +
                ", trueAnswerId=" + trueAnswerId +
                '}';
    }
}
