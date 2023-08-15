public class Main {

    public static void main(String[] args) {

        Vehicle transport = new Vehicle();
        Vehicle tesla = new ElectricCar();
        Vehicle ford = new Car();
        Vehicle bMW = new HybridCar();

        tesla.resource();
        ford.resource();
        bMW.resource();



    }
}
