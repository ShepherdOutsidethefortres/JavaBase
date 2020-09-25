package zhw.impl.facade;

import zhw.service.LetterProcess;

/***
 * 门面模式
 */
public class ModenPostOffice {
    private LetterProcess letterProcess = new LetterProcessImpl();

    /***
     * 写信、填地址、封装、投递，一体化
     * @param context
     * @param address
     */
    public void sendLetter(String context,String address){
        letterProcess.writeContext(context);
        letterProcess.fillEnvelope(address);
        letterProcess.letterIntoEnvelope();
        letterProcess.sendLetter();
    }
}
