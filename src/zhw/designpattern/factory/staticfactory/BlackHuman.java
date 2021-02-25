package zhw.designpattern.factory.staticfactory;

public class BlackHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黑种人在笑");
    }

    @Override
    public void cry() {
        System.out.println("黑种人在哭");
    }

    @Override
    public void talk() {
        System.out.println("黑种人在说话");
    }
}
