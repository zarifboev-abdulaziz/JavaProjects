package uz.pdp.model.payment;

public class PaymentMethod {

    private Integer id;
    private String name;
    private double commissionFee;

    public PaymentMethod() {
    }

    public PaymentMethod(Integer id, String name, double commissionFee) {
        this.id = id;
        this.name = name;
        this.commissionFee = commissionFee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCommissionFee() {
        return commissionFee;
    }

    public void setCommissionFee(double commissionFee) {
        this.commissionFee = commissionFee;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", commissionFee=" + commissionFee +
                '}';
    }
}
