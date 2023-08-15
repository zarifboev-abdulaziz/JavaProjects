package uz.pdp.threadExample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person extends Thread{
    private Ticket ticket;
    private String fullName;
    private int count;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        makeTicketOrder(ticket, fullName, count);
    }

    private void makeTicketOrder(Ticket ticket, String fullName, int count) {
        if (ticket.makeOrder(count)){
            System.out.println(fullName  +": Success");
        }else {
            System.err.println(fullName + ": Fail");
        }

    }

}
