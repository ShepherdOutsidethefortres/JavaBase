package demos.singleTon;

/**
 * 静态内部类
 * 优点：懒加载、线程安全、效率高
 * 结论：推荐使用
 * Created by huiwei.zhao on 2019/7/3.
 */
public class Singleton4 {
    private Singleton4() {
    }

    /***
     * 此时静态内部类会被装载，而且只会被装载一次，而且线程安全
     * @return
     */
    public static Singleton4 getInstance() {
        return Singleton4Inner.INSTANCE;
    }

    /**
     * 外部类装载时，静态内部类不会立即被装载
     */
    private static class Singleton4Inner {
        //类的静态属性只会在第一次加载的时候初始化
        private static final Singleton4 INSTANCE = new Singleton4();
    }
}
