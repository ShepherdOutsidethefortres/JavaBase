package zhw.impl.factory;

import zhw.abs.AbstractYellowHuman;

public class YellowFemaleHuman extends AbstractYellowHuman {
    /***
     * 忘记加性别了——抽象工厂模式加入性别
     */
    @Override
    public void sex() {
        System.out.println("该黄种人的性别为女");
    }
}
