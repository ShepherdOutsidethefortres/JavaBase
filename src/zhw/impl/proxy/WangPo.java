package zhw.impl.proxy;

import zhw.service.KindWomen;

/**
 * 接活
 * 金莲的代理人，得持有金莲的引用
 */
public class WangPo implements KindWomen {
    private KindWomen kindWomen;

    public WangPo() {
        this.kindWomen = new PanJinLian();
    }

    /***
     * kindwomen任意一种women的代理
     * @param kindWomen
     */
    public WangPo(KindWomen kindWomen) {
        this.kindWomen = kindWomen;
    }

    @Override
    public void makeEyesWithMan() {
        this.kindWomen.makeEyesWithMan();//被代理人抛媚眼
    }

    @Override
    public void happyWithMan() {
        this.kindWomen.happyWithMan();//被代理人.....
    }
}
