public class LegasyCar implements Car{

    @Override
    public void start() {
        System.out.println("This car is starting the engine.");
    }

    @Override
    public void run() {
        System.out.println("Legacy car is running.");
    }

    @Override
    public void stop() {
        System.out.println("Legacy car has stopped");
    }

    @Override
    public void fly() {
        System.out.println("Legacy car is flying.");
    }
}
