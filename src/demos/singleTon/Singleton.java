package demos.singleTon;

/**
 * 饿汉式写法一：静态常量写法
 * 优点：多线程安全
 * 缺点：在类装载的时候就完成实例化，没有达到lazy loading效果，如果从始至终从未使用过这个实例，则会造成内存的浪费
 * 结论：可用，可能造成内存浪费
 * Created by huiwei.zhao on 2019/7/3.
 */
public class Singleton {
    private Singleton(){}
    private final static Singleton instance=new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
}
