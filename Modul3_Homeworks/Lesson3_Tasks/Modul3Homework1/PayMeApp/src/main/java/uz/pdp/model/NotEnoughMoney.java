package uz.pdp.model;

public class NotEnoughMoney extends Exception{
    public NotEnoughMoney() {
        super("Hisobda pul yetarli emas!!!");
    }
}
