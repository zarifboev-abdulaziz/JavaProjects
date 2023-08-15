package uz.pdp.model;

public class Subject {
    //Fields
    private int id;
    private String name;
    private int numberOfTests;
    private float maxBall;
    private float scorePerTest;
    private double price;


    public Subject() {
    }

    public Subject(int id, String name, int numberOfTests, float maxBall, float scorePerTest, double price) {
        this.id = id;
        this.name = name;
        this.numberOfTests = numberOfTests;
        this.maxBall = maxBall;
        this.scorePerTest = scorePerTest;
        this.price = price;
    }

    public void setMaxBall(float maxBall) {
        this.maxBall = maxBall;
    }


    public float getMaxBall() {
        return maxBall;
    }

    public float getScorePerTest() {
        return scorePerTest;
    }

    public void setScorePerTest(float scorePerTest) {
        this.scorePerTest = scorePerTest;
    }

    public int getNumberOfTests() {
        return numberOfTests;
    }

    public void setNumberOfTests(int numberOfTests) {
        this.numberOfTests = numberOfTests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfTests=" + numberOfTests +
                ", maxBall=" + maxBall +
                ", price=" + price +
                '}';
    }
}
