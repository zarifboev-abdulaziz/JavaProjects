public class AddOperation implements AddInterface{
    @Override
    public void add(int a, int b) {
        System.out.println("a+b = " + (a + b));
    }
}
