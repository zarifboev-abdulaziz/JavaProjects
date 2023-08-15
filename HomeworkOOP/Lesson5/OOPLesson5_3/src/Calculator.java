public class Calculator {

                                                                       //Method-1 (ADD)
    //byte
    public static short add(byte a, byte b){
        return (short) (a + b);
    }
    //short
    public static short add(short a, short b){
        return (short) (a + b);
    }
    //Integer
    public static int add(int a, int b){
        return a + b;
    }
    //Long
    public static long add(long a, long b){
        return a + b;
    }
    //float
    public static float add(float a, float b){
        return a + b;
    }
    //double
    public static double add(double a, double b){
        return a + b;
    }

                                                                         //Method - 2 (SUB)
    //byte
    public static short sub(byte a, byte b){
        return (short) (a - b);
    }
    //short
    public static short sub(short a, short b){
        return (short) (a - b);
    }
    //Integer
    public static int sub(int a, int b){
        return a - b;
    }
    //Long
    public static long sub(long a, long b){
        return a - b;
    }
    //float
    public static float sub(float a, float b) {
        return a - b;
    }
    //double
    public static double sub(double a, double b){
        return a - b;
    }

                                                                            //Method - 3 MULTIPLY
    //byte
    public static short multiply(byte a, byte b){
        return (short) (a * b);
    }
    //short
    public static int multiply(short a, short b){
        return (int) (a * b);
    }
    //Integer
    public static int multiply(int a, int b){
        return a * b;
    }
    //Long
    public static long multiply(long a, long b){
        return a * b;
    }
    //float
    public static float multiply(float a, float b) {
        return a * b;
    }
    //double
    public static double multiply(double a, double b){
        return a * b;
    }
                                                                                //Method-4 DIVISION

    // byte
    public static short div(byte a, byte b){
        return (short) (a / b);
    }
    //short
    public static int div(short a, short b){
        return (int) (a / b);
    }
    //Integer
    public static int div(int a, int b){
        return a / b;
    }
    //Long
    public static long div(long a, long b){
        return a / b;
    }
    //float
    public static float div(float a, float b) {
        return a / b;
    }
    //double
    public static double div(double a, double b){
        return a / b;
    }
                                                                                  //Method - 5 ABS

    // byte
    public static short abs(byte a){
        if(a >= 0){ return  a;} else {return (short) -a;}
    }
    //short
    public static int abs(short a){
        return Math.abs(a);
    }
    //Integer
    public static int abs(int a){
        return Math.abs(a);
    }
    //Long
    public static long abs(long a){
        return Math.abs(a);
    }
    //float
    public static float abs(float a) {
        return Math.abs(a);
    }
    //double
    public static double abs(double a){
        return Math.abs(a);
    }
                                                                            //Method - 5 POW

    // byte
    public static short pow(byte a){
        return (short) (a*a);
    }
    //short
    public static int pow(short a){
        return a*a;
    }
    //Integer
    public static int pow(int a){
        return a*a;
    }
    //Long
    public static long pow(long a){
        return a*a;
    }
    //float
    public static float pow(float a) {
        return a*a;
    }
    //double
    public static double pow(double a){
        return a*a;
    }


}
