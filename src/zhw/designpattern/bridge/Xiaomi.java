package zhw.designpattern.bridge;

public class Xiaomi implements Brand {
    @Override
    public void open() {
        System.out.println("小米手机在开机");
    }

    @Override
    public void close() {
        System.out.println("小米手机在关机");
    }

    @Override
    public void call() {
        System.out.println("小米手机在打电话");
    }
}
