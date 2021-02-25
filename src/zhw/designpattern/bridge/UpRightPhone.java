package zhw.designpattern.bridge;

public class UpRightPhone extends Phone {
    public UpRightPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open(){
        super.open();
        System.out.println("直立式手机");
    }

    @Override
    protected void close() {
        super.close();
        System.out.println("直立式手机");
    }

    @Override
    protected void call() {
        super.call();
        System.out.println("直立式手机");
    }
}
