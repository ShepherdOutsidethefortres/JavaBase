package zhw.impl.factory;

import zhw.service.Human;

public class BlackHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黑人也还行");
    }

    /***
     * 忘记加性别了——抽象工厂模式加入性别
     */
    @Override
    public void sex() {
        return;
    }
}
