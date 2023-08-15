public interface Animal {

    void sound();

    default void eat(){

    }

    static void run(){
        System.out.println("Animal is running");
    }

}
