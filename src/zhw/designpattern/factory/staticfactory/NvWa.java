package zhw.designpattern.factory.staticfactory;

public class NvWa {
    public static void main(String[] args) {
        Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
        yellowHuman.laugh();
        yellowHuman.cry();
        yellowHuman.talk();

        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.laugh();
        whiteHuman.cry();
        whiteHuman.talk();

        Human blackHuman = HumanFactory.createHuman(BlackHuman.class);
        blackHuman.laugh();
        blackHuman.cry();
        blackHuman.talk();

    }
}
