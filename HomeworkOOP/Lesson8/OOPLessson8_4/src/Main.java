public class Main {

    public static void main(String[] args) {

        System.out.println("1.Add operation by class");
        //Functional Interface N-1; Class
        AddOperation addOperation = new AddOperation();
        addOperation.add(5,7);

        System.out.println("2.Subtract operation by anonim class");
        //Functional Interface N-2; Anonim
        SubInterface subInterface = new SubInterface() {
            @Override
            public void sub(int a, int b) {
                System.out.println("a-b = " + (a-b));
            }
        };

        subInterface.sub(6,9);

        System.out.println("3.Division operation by lambda and others...");
        //Functional Interface N-3; Lambda
        DivInterface divInterface = (a,b) -> System.out.println("a/b = " + (a/b));

        divInterface.div(8,3);

        //Functional Interface N-4; Lambda
        MultInterface multiplication = (a, b) -> {
            System.out.println("a*b = " + (a*b));
        };
        multiplication.mult(2,9);

        //Functional Interface N-5; Lambda
        Power2Interface power = (a)-> System.out.println("a^2" + a*a);
        power.pow2(8);


    }
}
