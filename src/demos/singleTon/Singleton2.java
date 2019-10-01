package demos.singleTon;

/**
 * 懒汉式
 * 懒加载
 * 线程不安全
 * 线程安全 方法上加synchronized，加锁影响效率
 * Created by huiwei.zhao on 2019/7/3.
 */
public class Singleton2 {
    private Singleton2(){}
    private static Singleton2 singleton2;

    public static Singleton2 getInstance(){
        if (singleton2==null){
            singleton2=new Singleton2();
        }

        return singleton2;
    }

    public static synchronized Singleton2 getInstance(int a){
        if (singleton2==null){
            singleton2=new Singleton2();
        }

        return singleton2;
    }
}
