package demos.singleTon;

/**
 * 枚举单例
 * 非懒加载
 * 线程安全
 * 可避免经常序列、反序列化对象
 *
 * 单例模式总结：
 * 一般情况下，不建议使用懒汉方式，建议使用饿汉方式。只有在要明确实现 azy loading 效果时，才会使用登记/静态内部类方式。如果涉及到反序列化创建对象时，可以尝试使用枚举方式。如果有其他特殊的需求，可以考虑使用双检锁方式。
 * Created by huiwei.zhao on 2019/7/3.
 */
public enum Singleton5 {
    INSTANCE;
}
