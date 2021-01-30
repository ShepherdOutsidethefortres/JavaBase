package demos.singleTon;

public class SingletonTest02 {
    public static void main(String[] args) {
        Singleton02 instance = Singleton02.getInstance();
        Singleton02 instance1 = Singleton02.getInstance();

        System.out.println(instance == instance1);
        System.out.println(instance.hashCode() == instance1.hashCode());
    }
}

/***
 * 饿汉式方法二：静态代码块
 * 优缺点同饿汉式静态常量实现
 */
class Singleton02 {
    private static Singleton02 instance;

    private Singleton02() {
    }

    static {
        instance = new Singleton02();
    }

    public static Singleton02 getInstance() {
        return instance;
    }

}
