package zhw.impl.factory;

import zhw.abs.AbstractBlackHuman;

public class BlackMaleHuman extends AbstractBlackHuman {
    /***
     * 忘记加性别了——抽象工厂模式加入性别
     */
    @Override
    public void sex() {
        System.out.println("该黑种人的性别为男");
    }
}
