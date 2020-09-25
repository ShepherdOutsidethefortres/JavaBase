package zhw.container;

import zhw.service.Human;
import zhw.utils.ClassUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/***
 * 工厂模式
 */
@SuppressWarnings("all")
public class HumanFactory {
    //定义一个map，初始化的Human对象放入这里
    private static HashMap<String, Human> humans = new HashMap<String, Human>();

    /***
     * 造指定种别的人类
     * @param clazz
     * @return
     */
    public static Human createHuman(Class clazz) {
        Human human = null;

        try {
            //如果map中有，则直接取出，不用初始化了
            //延迟始化
            if (humans.containsKey(clazz.getSimpleName())) {
                human = humans.get(clazz.getSimpleName());
            } else {
                //初始化
                human = (Human) Class.forName(clazz.getName()).newInstance();
                //放到map中
                humans.put(clazz.getSimpleName(), human);
            }
        } catch (IllegalAccessException e) {//定义的人类有问题
            System.out.println("人类定义错误");
        } catch (InstantiationException e) {
            System.out.println("必须指定人类的颜色");
        } catch (ClassNotFoundException e) {//定义的人类找不到
            System.out.println("混蛋，你定义的人类找不到");
        }

        return human;
    }

    /***
     * 随机生成人类
     * @return
     */
    public static Human createHuman() {
        Human human = null;
        Random random = new Random();
        List<Class> concreteHumanList = ClassUtils.getAllClassByInterface(Human.class);
        int rand = random.nextInt(concreteHumanList.size());
        human = createHuman(concreteHumanList.get(rand));
        return human;
    }
}
