package zhw.impl.strategy;

import zhw.service.IStrategy;

/***
 * 计谋二：开绿灯
 */
public class GivenGreenLight implements IStrategy {
    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯，放行！");
    }
}
