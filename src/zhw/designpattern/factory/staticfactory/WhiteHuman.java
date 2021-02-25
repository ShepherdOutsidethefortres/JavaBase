package zhw.designpattern.factory.staticfactory;

public class WhiteHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("白种人在笑");
    }

    @Override
    public void cry() {
        System.out.println("白种人在哭");
    }

    @Override
    public void talk() {
        System.out.println("白种人在说话");
    }
}
