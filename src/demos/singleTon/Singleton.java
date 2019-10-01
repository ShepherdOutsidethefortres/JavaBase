package demos.singleTon;

/**
 * 饿汉式
 * 类加载时就初始化
 * 多线程安全
 * Created by huiwei.zhao on 2019/7/3.
 */
public class Singleton {
    private Singleton(){}
    private static Singleton singleton=new Singleton();

    public static Singleton getInstance(){
        return singleton;
    }
}
