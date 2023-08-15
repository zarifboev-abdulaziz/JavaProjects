package uz.pdp.model;

public class Answer {
    private int id;
    private int testId;
    private String body;

    public Answer() {
    }

    public Answer(int id, int testId, String body) {
        this.id = id;
        this.testId = testId;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", testId=" + testId +
                ", body='" + body + '\'' +
                '}';
    }
}
