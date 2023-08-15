package uz.pdp;
//
public class Main {
    public static void main(String[] args) {
        findTextLength("");

    }

    public static void findTextLength(String str){
        int length = str.length();

        if (length == 0 ){
            try {
                throw new NoTextException();
            } catch (NoTextException e) {
                System.err.println(e.getMessage());
            }
        }else {
            System.out.println("Matn uzunligi: " + length);
        }
    }
}
