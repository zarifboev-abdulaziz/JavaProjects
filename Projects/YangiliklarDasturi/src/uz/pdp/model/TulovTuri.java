package uz.pdp.model;

public class TulovTuri {
    int id;
    String nomi;
    double komissiya;

    public TulovTuri() {
    }

    public TulovTuri(int id, String nomi, double komissiya) {
        this.id = id;
        this.nomi = nomi;
        this.komissiya = komissiya;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomi() {
        return nomi;
    }

    public void setNomi(String nomi) {
        this.nomi = nomi;
    }

    public double getKomissiya() {
        return komissiya;
    }

    public void setKomissiya(double komissiya) {
        this.komissiya = komissiya;
    }

    @Override
    public String toString() {
        return "TulovTuri{" +
                "id=" + id +
                ", nomi='" + nomi + '\'' +
                ", komissiya=" + komissiya +
                '}';
    }
}
