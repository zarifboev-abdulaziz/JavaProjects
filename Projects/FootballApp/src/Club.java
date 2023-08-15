import java.util.ArrayList;
import java.util.List;

public class Club {

    //Properties
    private int clubId;
    private String name;
    private double budget;
    private int sinceYear;
    List<Player> players = new ArrayList<>();

    //Constructor
    public Club() {
    }

    public Club(int clubId, String name, double budget, int sinceYear) {
        this.clubId = clubId;
        this.name = name;
        this.budget = budget;
        this.sinceYear = sinceYear;

    }

    //Getter and Setters


    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getSinceYear() {
        return sinceYear;
    }

    public void setSinceYear(int sinceYear) {
        this.sinceYear = sinceYear;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    //to String


    @Override
    public String toString() {
        return "Club{" +
                "clubId=" + clubId +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", sinceYear=" + sinceYear +
                '}';
    }
}
