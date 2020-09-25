package zhw.execute;

import zhw.container.StrategyContext;
import zhw.impl.strategy.BackDoor;
import zhw.impl.strategy.BlockEnemy;
import zhw.impl.strategy.GivenGreenLight;

/**
 * Created by huiweizhao on 2020/4/20.
 * 这个Main就是赵云类——使用者
 */
public class StrategyMain {
    public static void main(String[] args) {
        StrategyContext strategyContext;
        System.out.println("——————————————————刚刚到吴国的时候拆第一个————————————————————");
        strategyContext = new StrategyContext(new BackDoor());
        strategyContext.operate();

        System.out.println("——————————————————刘备乐不思蜀了，拆第二个了————————————————————");
        strategyContext = new StrategyContext(new GivenGreenLight());
        strategyContext.operate();

        System.out.println("——————————————————孙权的追兵到了，拆第三个————————————————————");
        strategyContext = new StrategyContext(new BlockEnemy());
        strategyContext.operate();

        /*
         * 问题来了:赵云实际不知道是那个策略呀，他只知道拆第一个锦囊，
         * 而不知道是BackDoor这个妙计，咋办? 似乎这个策略模式已经把计谋名称写出来了 *
         * 错!BackDoor、GivenGreenLight、BlockEnemy只是一个代码，你写成first、second、 third，没人会说你错!
         *
         * 策略模式的好处就是:体现了高内聚低耦合的特性呀，缺点嘛，这个那个，我回去再查查
         */
    }
}
