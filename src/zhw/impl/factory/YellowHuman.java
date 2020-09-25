package zhw.impl.factory;

import zhw.service.Human;

public class YellowHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黄种人了不起");
    }

    /***
     * 忘记加性别了——抽象工厂模式加入性别
     */
    @Override
    public void sex() {
        return;
    }
}
