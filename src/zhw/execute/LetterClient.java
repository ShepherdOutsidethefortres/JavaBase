package zhw.execute;

import zhw.impl.facade.LetterProcessImpl;

/***
 * 客户写信还得知道步骤，期望客户只需要把信的内容和地址给到邮局，就可以了
 */
public class LetterClient {
    public static void main(String[] args) {
        /***
         * 开始写信
         */
        LetterProcessImpl letterProcess = new LetterProcessImpl();

        //写信
        letterProcess.writeContext("Hello，It's me，do you know who I am? I'm your old lover.I'd like to ....");

        //写信封
        letterProcess.fillEnvelope("Happy Road No. 666,God Province,Heaven");

        //信塞入信封
        letterProcess.letterIntoEnvelope();

        //投递信件
        letterProcess.sendLetter();
    }
}
