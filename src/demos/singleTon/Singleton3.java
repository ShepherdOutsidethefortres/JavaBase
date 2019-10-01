package demos.singleTon;

/**
 * 双重锁校验
 * 懒加载
 * 线程安全
 * 效率低
 * volatile锁
 * synchronized锁
 * Created by huiwei.zhao on 2019/7/3.
 */
public class Singleton3 {
    private Singleton3() {
    }

    private static volatile Singleton3 singleton3;

    public static Singleton3 getInstance() {
        if (singleton3 == null) {
            synchronized (Singleton3.class) {
                if (singleton3 == null) {
                    singleton3 = new Singleton3();
                }
            }
        }

        return singleton3;
    }
}
