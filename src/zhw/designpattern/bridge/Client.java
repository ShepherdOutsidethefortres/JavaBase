package zhw.designpattern.bridge;

public class Client {
    public static void main(String[] args) {
        Phone phone1=new UpRightPhone(new Xiaomi());

        phone1.open();
        phone1.close();
        phone1.call();
    }
}
