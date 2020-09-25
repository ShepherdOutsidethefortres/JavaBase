package zhw.dynamicproxy;

/***
 * 车站类
 */
public class Station implements TicketService {
    /***
     * 问询
     */
    @Override
    public void inquire() {
        System.out.println("车站问询........");
    }

    /***
     * 售票
     */
    @Override
    public void sellTicket() {
        System.out.println("车站售票......");
    }

    /***
     * 退票
     */
    @Override
    public void withdraw() {
        System.out.println("车站退票......");
    }
}
