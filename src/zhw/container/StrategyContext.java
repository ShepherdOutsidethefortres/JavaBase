package zhw.container;

import zhw.service.IStrategy;

/***
 * 锦囊——存放计谋的地方
 */
public class StrategyContext {
    private IStrategy strategy;

    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void operate() {
        this.strategy.operate();
    }
}
