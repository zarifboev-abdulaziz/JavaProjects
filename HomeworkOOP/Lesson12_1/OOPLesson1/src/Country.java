public class Country {
    String name;
    String capital;
    double budget;
    double population;

    public Country() {
    }

    public Country(String name, String capital, double budget, double population) {
        this.name = name;
        this.capital = capital;
        this.budget = budget;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "About Country: " +
                "Name : " + name + '\'' +
                ", capital city: '" + capital + '\'' +
                ", GDP: " + budget + "$" +
                ", population : " + population +
                '}';
    }
}
