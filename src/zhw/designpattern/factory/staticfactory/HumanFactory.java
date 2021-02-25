package zhw.designpattern.factory.staticfactory;

public class HumanFactory {
    public static Human createHuman(Class clazz) {
        Human human = null;
        try {
            human = (Human) Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            System.out.println("必须指定人类的颜色");
        } catch (IllegalAccessException e) {
            System.out.println("人类定义错误");
        } catch (ClassNotFoundException e) {
            System.out.println("你要指定的人类不存在");
        }

        return human;
    }

    public static Human createHuman() {
        Human human = null;

        return human;
    }
}
