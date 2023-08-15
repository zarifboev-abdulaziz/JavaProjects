public class Car extends WheeledVehicle{
    String carType;


    @Override
    void start() {
        System.out.println("Car is starting the engine.");
    }

    @Override
    void stop() {
        System.out.println("Car has stopped.");
    }

}
