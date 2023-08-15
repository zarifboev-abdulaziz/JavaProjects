public class Figure {

    //Methods
    //AREA
    public void area(float r){
        System.out.println("Doiraning yuzi: " + (float)Math.PI*r*r);
    }

    public void area(float a, float b){
        System.out.println("To'g'ri to'rtburchakning yuzi: " + a*b);
    }

    //PERIMETR
    public void perimetr(float r){
        System.out.println("Doiraning uzunligi: " + (float)2*Math.PI*r);
    }

    public void perimetr(float a, float b){
        System.out.println("To'g'ri to'rtburchakning perimetri: " + a+b);
    }

    //PRINT
    public void print(float r){
        System.out.println("Doiraning radiusi: " + r);
    }

    public void print(float a, float b){
        System.out.println("To'g'ri to'rtburchak tomonlari: " + a + " va " +b);
    }



}
