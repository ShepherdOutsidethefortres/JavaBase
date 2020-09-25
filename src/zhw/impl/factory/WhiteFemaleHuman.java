package zhw.impl.factory;

import zhw.abs.AbstractWhiteHuman;

public class WhiteFemaleHuman extends AbstractWhiteHuman {
    /***
     * 忘记加性别了——抽象工厂模式加入性别
     */
    @Override
    public void sex() {
        System.out.println("该白种人的性别为男");
    }
}
