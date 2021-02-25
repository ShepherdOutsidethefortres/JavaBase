package zhw.designpattern.factory.staticfactory;

public class YellowHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黄种人在笑");
    }

    @Override
    public void cry() {
        System.out.println("黄种人在哭");
    }

    @Override
    public void talk() {
        System.out.println("黄种人在说话");
    }
}
