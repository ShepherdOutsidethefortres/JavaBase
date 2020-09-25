package zhw.abs;

import zhw.service.Human;

public abstract class AbstractYellowHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黄种人会笑");
    }
}
