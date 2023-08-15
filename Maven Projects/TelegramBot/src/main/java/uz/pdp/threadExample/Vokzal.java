package uz.pdp.threadExample;

public class Vokzal {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Person person = new Person(ticket, "Eldor", 4);
        Person person1 = new Person(ticket, "Asad", 4);

        person.start();
        person1.start();

    }
}
