public class Pen {
    //Properties
    private double ink;  // siyoh
    private double inkConsumption; // bitt harf uchun sarflanadigan siyoh miqdori

    //Constructors
    //C1
    Pen(){

    }

    //C2
    public Pen(double ink) {
        this.ink = ink;
        this.inkConsumption = inkConsumption;
    }

    // Methods
    //M1
    public void write(String word){
        if(ink > 0) {

            String result = "";
            for (int i = 0; i < word.length() && ink > 0; i++) {
                if(Character.isDigit(word.charAt(i))){ if(ink >= 6){ink = ink - 6;} else {ink=ink-ink;}}
                if(Character.isUpperCase(word.charAt(i))){if(ink >= 6){ink = ink - 6;} else {ink=ink-ink;}}
                if(Character.isLowerCase(word.charAt(i))){if(ink >= 3){ink = ink - 3;} else {ink=ink-ink;}}
                result = result + word.charAt(i);
            }
            System.out.println("Yozildi: " + result);


        } else {
            System.out.println("Ruchkaning siyohi qolmadi. Iltimos siyohni to'ldiring.");
        }
    }

    public void fillInk(){

    }

    //Getters and Setters

    public double getInk() {
        return ink;
    }

    public void setInk(double ink) {
        if(ink > 0){
        this.ink = this.ink + ink;
        }
    }

    public double getInkConsumption() {
        return inkConsumption;
    }

    public void setInkConsumption(double inkConsumption) {
        this.inkConsumption = inkConsumption;
    }

}
