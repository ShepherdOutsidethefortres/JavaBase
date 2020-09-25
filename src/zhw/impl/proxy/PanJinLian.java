package zhw.impl.proxy;

import zhw.service.KindWomen;

/***
 * 干活
 */
public class PanJinLian implements KindWomen {
    @Override
    public void makeEyesWithMan() {
        System.out.println("金莲抛媚眼");
    }

    @Override
    public void happyWithMan() {
        System.out.println("金莲......");
    }
}
