public class PropellerPlane extends Aircraft{
    int propellers;


    //Methods
    @Override
    void start() {
        System.out.println("Propeller Plane is starting the engine");
    }

    @Override
    void stop() {
        System.out.println("Propeller Plane is stopping the engine");

    }

    @Override
    void takeoff() {
        System.out.println("Propeller Plane is taking off...");

    }

    @Override
    void land() {
        System.out.println("Propeller Plane is landing.");

    }
}
