package zhw.impl.strategy;

import zhw.service.IStrategy;

/***
 * 计谋三：阻击追兵
 */
public class BlockEnemy implements IStrategy {
    @Override
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}
