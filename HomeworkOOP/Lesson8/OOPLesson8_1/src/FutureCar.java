public class FutureCar implements Car{
    @Override
    public void start() {
        System.out.println("Future car is starting the engine.");

    }

    @Override
    public void run() {
        System.out.println("Future car is running.");

    }

    @Override
    public void stop() {
        System.out.println("Future car has stopped");

    }

    @Override
    public void fly() {
        System.out.println("Future car is flying.");
    }
}
