import service.TicketSaleThread;

public class Main {
    public static void main(String[] args) {
        TicketSaleThread ticketSaleThread = new TicketSaleThread();
        Thread t1 = new Thread(ticketSaleThread, "火车站售票窗口");
        Thread t2 = new Thread(ticketSaleThread, "12306官网");
        Thread t3 = new Thread(ticketSaleThread, "携程App");

        t1.start();
        t2.start();
        t3.start();
    }
}
