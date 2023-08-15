package uz.pdp.model;

import uz.pdp.model.enums.Status;

public class Yangiliklar {
   private int id;
   private String sarlavha;
   private String matn;
   private Foydalanuvchi aftor;
   private Status status;

    public Yangiliklar() {
    }

    public Yangiliklar(int id, String sarlavha, String matn, Foydalanuvchi aftor, Status status) {
        this.id = id;
        this.sarlavha = sarlavha;
        this.matn = matn;
        this.aftor = aftor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSarlavha() {
        return sarlavha;
    }

    public void setSarlavha(String sarlavha) {
        this.sarlavha = sarlavha;
    }

    public String getMatn() {
        return matn;
    }

    public void setMatn(String matn) {
        this.matn = matn;
    }

    public Foydalanuvchi getAftor() {
        return aftor;
    }

    public void setAftor(Foydalanuvchi aftor) {
        this.aftor = aftor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Yangiliklar{" +
                "id=" + id +
                ", sarlavha='" + sarlavha + '\'' +
                ", matn='" + matn + '\'' +
                ", aftor=" + aftor +
                ", status=" + status +
                '}';
    }
}
