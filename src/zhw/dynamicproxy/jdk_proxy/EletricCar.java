package zhw.dynamicproxy.jdk_proxy;

/****
 * 电动车类，实现两个接口
 */
public class EletricCar implements Rechargable,Vehicle {
    @Override
    public void recharge() {
        System.out.println("Electric Car is Recharging...");
    }

    @Override
    public void drive() {
        System.out.println("Electric Car is Moving silently...");
    }
}
