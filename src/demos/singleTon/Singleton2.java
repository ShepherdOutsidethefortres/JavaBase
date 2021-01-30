package demos.singleTon;

/**
 * 懒汉式
 * 优点：懒加载
 * 缺点：线程不安全
 * 结论：该方式在实际中不可用
 * 线程安全 方法上加synchronized，加锁影响效率。不推荐使用
 * Created by huiwei.zhao on 2019/7/3.
 */
public class Singleton2 {
    private Singleton2(){}
    private static Singleton2 instance;

    /***
     * 线程不安全
     * @return
     */
    public static Singleton2 getInstance(){
        if (instance==null){
            instance=new Singleton2();
        }
        return instance;
    }

    /***
     * 线程安全、同步方法
     * @return
     */
    public static synchronized Singleton2 getSynInstance(){
        if (instance==null){
            instance=new Singleton2();
        }

        return instance;
    }

    /***
     * 线程不安全、同步代码块
     * 缺点：多个线程进入了if判断后，还是会存在多线程安全问题
     * 结论：不可用
     * @return
     */
    public static Singleton2 getSynCodeInstance(){
        if (instance==null){
            synchronized (Singleton2.class){
                instance=new Singleton2();
            }
        }

        return instance;
    }
}
