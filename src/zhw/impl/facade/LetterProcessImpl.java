package zhw.impl.facade;

import zhw.service.LetterProcess;

/***
 * 写信过程的实现类
 */
public class LetterProcessImpl implements LetterProcess {
    /***
     * 写信
     * @param context
     */
    @Override
    public void writeContext(String context) {
        System.out.println("填写信的内容：" + context);
    }

    /***
     * 写信封
     * @param address
     */
    @Override
    public void fillEnvelope(String address) {
        System.out.println("填写收件人地址及姓名：" + address);
    }

    /***
     * 信放入信封
     */
    @Override
    public void letterIntoEnvelope() {
        System.out.println("把信放到信封中。");
    }

    /***
     * 送信
     */
    @Override
    public void sendLetter() {
        System.out.println("邮递信件");
    }
}
