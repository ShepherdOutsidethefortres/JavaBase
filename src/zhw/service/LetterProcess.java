package zhw.service;

/***
 * 写信的过程
 */
public interface LetterProcess {
    /***
     * 写信
     * @param context
     */
    void writeContext(String context);

    /***
     * 写信封
     * @param address
     */
    void fillEnvelope(String address);

    /***
     * 信放入信封
     */
    void letterIntoEnvelope();

    /***
     * 送信
     */
    void sendLetter();
}
