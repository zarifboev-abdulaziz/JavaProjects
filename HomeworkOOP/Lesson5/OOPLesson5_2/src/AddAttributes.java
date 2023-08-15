public class AddAttributes {


    //=======Method overloading=======
    //Methods

    //N-1
    public int add(int a, int b){
        return a+b;
    }

    //N-2
    public double add(int a, double b){
        return a+b;
    }

    //N-3
    public double add(double a, double b, double c){
        return a + b + c;
    }

    //N-4
    public String add(String a, String b){
        return a + b;
    }

    //N-5
    public String add(int a, String str){
        return str + a;
    }



}
