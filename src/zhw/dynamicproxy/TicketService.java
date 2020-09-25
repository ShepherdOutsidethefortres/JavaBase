package zhw.dynamicproxy;

public interface TicketService {

    /***
     * 问询
     */
    void inquire();

    /***
     * 售票
     */
    void sellTicket();

    /***
     * 退票
     */
    void withdraw();
}
