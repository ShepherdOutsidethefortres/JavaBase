package demos.singleTon;

/**
 * 双重锁校验
 * 优点：懒加载、线程安全、保证了效率（后续线程不会去做同步代码的检测）
 * volatile锁
 * synchronized锁
 * 结论：推荐使用
 * Created by huiwei.zhao on 2019/7/3.
 */
public class Singleton3 {
    private Singleton3() {
    }

    //volatile：修改立即更新到主存。轻量级的synchronized
    private static volatile Singleton3 instance;

    public static Singleton3 getInstance() {
        //只有一个线程能进入这里
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }

        return instance;
    }
}
