package demos.singleTon;

/**
 * 静态内部类
 * 懒加载
 * 线程安全
 * 效率高
 * Created by huiwei.zhao on 2019/7/3.
 */
public class Singleton4 {
    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        return Singleton4Inner.INSTANCE;
    }

    private static class Singleton4Inner {
        private static final Singleton4 INSTANCE = new Singleton4();
    }
}
