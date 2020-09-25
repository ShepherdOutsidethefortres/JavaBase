package zhw.impl.facade;

/***
 * 用户不用关心投递信件的内部过程，只需要把信的内容和邮寄地址交给邮局，让邮局自己去完成就好
 */
public class NewLetterClient {
    public static void main(String[] args) {
        ModenPostOffice modenPostOffice = new ModenPostOffice();
        //你只要把信的内容和收信人地址给他，他会帮你完成一系列的工作;
        String address = "Happy Road No. 666,God Province,Heaven";
        //定义一个地址
        String context = "Hello,It's me,do you know who I am? I'm your old lover. I'd like to....";

        modenPostOffice.sendLetter(context, address);
    }
}
