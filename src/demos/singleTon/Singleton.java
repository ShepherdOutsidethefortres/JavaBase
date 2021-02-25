package demos.singleTon;

/**
 * 饿汉式写法一：静态常量写法
 * 优点：多线程安全
 * 缺点：在类装载的时候就完成实例化，没有达到lazy loading效果，如果从始至终从未使用过这个实例，则会造成内存的浪费
 * 结论：可用，可能造成内存浪费
 *
 * 单例模式保证了系统内存中该类只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的对象，使用单例模式可以提高系统性能。
 * （创建对象耗时过多、耗费资源过多等重量级对象，工具类对象，数据源、session工厂等频繁访问数据库或文件的对象）
 * Created by huiwei.zhao on 2019/7/3.
 */
public class Singleton {
    private Singleton(){}
    private final static Singleton instance=new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
}
