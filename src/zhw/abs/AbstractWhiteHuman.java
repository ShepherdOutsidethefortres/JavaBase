package zhw.abs;

import zhw.service.Human;

public abstract class AbstractWhiteHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("白种人会笑");
    }
}
