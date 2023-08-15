package uz.pdp.model;

public class Subject {
    private int id;
    private String name;
    private double price;
    private int numberOfTests;
    private double maxBall;
    private int scorePerTest;

    public Subject() {
    }

    public Subject(int id, String name, double price, int numberOfTests, double maxBall, int scorePerTest) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.numberOfTests = numberOfTests;
        this.maxBall = maxBall;
        this.scorePerTest = scorePerTest;
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

    public int getNumberOfTests() {
        return numberOfTests;
    }

    public void setNumberOfTests(int numberOfTests) {
        this.numberOfTests = numberOfTests;
    }

    public double getMaxBall() {
        return maxBall;
    }

    public void setMaxBall(double maxBall) {
        this.maxBall = maxBall;
    }

    public int getScorePerTest() {
        return scorePerTest;
    }

    public void setScorePerTest(int scorePerTest) {
        this.scorePerTest = scorePerTest;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", numberOfTests=" + numberOfTests +
                ", maxBall=" + maxBall +
                ", scorePerTest=" + scorePerTest +
                '}';
    }
}
