package uz.pdp.model;

public class Transaksiya {
    int id;
    Foydalanuvchi aftor;
    Yangiliklar yangilik;
    double summa;
    double komissiya;

    public Transaksiya() {
    }

    public Transaksiya(int id, Foydalanuvchi aftor, Yangiliklar yangilik, double summa, double komissiya) {
        this.id = id;
        this.aftor = aftor;
        this.yangilik = yangilik;
        this.summa = summa;
        this.komissiya = komissiya;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Foydalanuvchi getAftor() {
        return aftor;
    }

    public void setAftor(Foydalanuvchi aftor) {
        this.aftor = aftor;
    }

    public Yangiliklar getYangilik() {
        return yangilik;
    }

    public void setYangilik(Yangiliklar yangilik) {
        this.yangilik = yangilik;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public double getKomissiya() {
        return komissiya;
    }

    public void setKomissiya(double komissiya) {
        this.komissiya = komissiya;
    }

    @Override
    public String toString() {
        return "Transaksiya{" +
                "id=" + id +
                ", aftor=" + aftor +
                ", yangilik=" + yangilik +
                ", summa=" + summa +
                ", komissiya=" + komissiya +
                '}';
    }
}
