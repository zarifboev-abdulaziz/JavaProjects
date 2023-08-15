public class Savol5 {
    public static void main(String[] args) {

        float speed1 = 70;
        float speed2 = 50;
        float speed3 = 100;

        System.out.println(carSpeed(speed1));
        System.out.println(carSpeed(speed2));
        System.out.println(carSpeed(speed3));

    }
    public static float carSpeed(float n){
        return (float) (n/3.6);
    }

}
