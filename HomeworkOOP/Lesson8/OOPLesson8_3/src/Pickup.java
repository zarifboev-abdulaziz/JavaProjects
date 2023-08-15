public class Pickup implements CargoAuto, PassengerAuto{


    @Override
    public void transportPassengers() {
        PassengerAuto.super.transportPassengers();
    }

}
