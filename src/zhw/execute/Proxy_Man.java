package zhw.execute;

import zhw.impl.proxy.WangPo;

/***
 * 西门
 */
public class Proxy_Man {
    public static void main(String[] args) {
        WangPo wangPo = new WangPo();
        wangPo.makeEyesWithMan();
        wangPo.happyWithMan();
    }
}
