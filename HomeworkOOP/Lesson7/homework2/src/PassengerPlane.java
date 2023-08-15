public class PassengerPlane extends Aircraft{
    int passengers;

    //methods
    @Override
     void start(){
        System.out.println("Passenger Plane is starting the engine");
    }
    @Override
    void stop(){
        System.out.println("Passenger Plane is stopping the engine");
    }
    @Override
     void takeoff(){
        System.out.println("Passenger Plane is taking off...");
    }
    @Override
    void land(){
        System.out.println("Passenger Plane is landing.");
    }

}
