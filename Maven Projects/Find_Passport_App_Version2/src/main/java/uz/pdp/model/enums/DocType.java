package uz.pdp.model.enums;

public enum DocType {
    PASSPORT("Passport"),
    DRIVER_LICENCE("Haydovchilik guvohnomasi"),
    ID_CARD("ID karta"),
    STUDENT_CARD("Talabalik guvohnomasi"),
    CREDIT_CARD("Plastik karta"),
    CERTIFICATE("Diplom, sertifikat"),
    OTHER("Boshqa");


    private String uzName;

    DocType(String uzName) {
        this.uzName = uzName;
    }

    public String getUzName() {
        return uzName;
    }

}
