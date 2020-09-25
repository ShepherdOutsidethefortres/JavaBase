package zhw.abs;

import zhw.container.HumanEnum;
import zhw.service.Human;
import zhw.service.HumanFactory;

/***
 * 产品等级
 */
public abstract class AbstractHumanFactory implements HumanFactory {

    /***
     * 简化实现类的代码工作量
     * @param humanEnum
     * @return
     */
    protected Human createHuman(HumanEnum humanEnum) {
        Human human = null;

        if (!humanEnum.getValue().equals("")) {
            try {
                human = (Human) Class.forName(humanEnum.getValue()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return human;
    }
}
