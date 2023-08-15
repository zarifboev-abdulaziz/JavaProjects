public class FlighterPlane extends Aircraft{
    Object engine;


    //Methods
    @Override
    void start() {
        System.out.println("Flighter Plane is starting the engine");
    }

    @Override
    void stop() {
        System.out.println("Flighter Plane is stopping the engine");

    }

    @Override
    void takeoff() {
        System.out.println("Flighter Plane is taking off...");

    }

    @Override
    void land() {
        System.out.println("Flighter Plane is landing.");

    }
}
