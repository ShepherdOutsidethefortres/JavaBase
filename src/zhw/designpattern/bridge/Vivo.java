package zhw.designpattern.bridge;

public class Vivo implements Brand {
    @Override
    public void open() {
        System.out.println("Vivo手机在开机");
    }

    @Override
    public void close() {
        System.out.println("Vivo手机在关机");
    }

    @Override
    public void call() {
        System.out.println("Vivo手机在打电话");
    }
}
