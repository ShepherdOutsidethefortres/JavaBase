package zhw.designpattern.strategy.origin;

public class ConcreteStrategyB implements Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("具体策略B的策略方法被调用");
    }
}
