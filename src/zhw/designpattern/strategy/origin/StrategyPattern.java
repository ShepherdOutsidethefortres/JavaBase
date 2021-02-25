package zhw.designpattern.strategy.origin;

public class StrategyPattern {
    public static void main(String[] args) {
        Context context = new Context();
        Strategy strategy = new ConcreteStrategyA();

        context.setStrategy(strategy);
        context.strategyMethod();

        System.out.println("---------");

        strategy = new ConcreteStrategyB();
        context.setStrategy(strategy);
        context.strategyMethod();
    }
}
