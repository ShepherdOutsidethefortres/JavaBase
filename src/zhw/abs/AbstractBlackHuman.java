package zhw.abs;

import zhw.service.Human;

public abstract class AbstractBlackHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黑种人会笑");
    }
}
