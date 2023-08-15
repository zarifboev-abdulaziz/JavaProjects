public class SalariedEmployee extends Person implements Office{
    //fields
    double weeklyPay;

    //Methods
    void setWage(double weeklyPay){
        this.weeklyPay = weeklyPay;
    }

    void moveOffice(){

    }

    @Override
    public void setNumber() {

    }

    @Override
    public long getNumber() {
        return 0;
    }

    @Override
    public void setOccupant() {

    }

    @Override
    public String getOccupant() {
        return null;
    }
}
