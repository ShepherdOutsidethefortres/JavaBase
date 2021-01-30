package beans.effectivejava.singleton;

/***
 * 单元素的枚举类型经常成为实现 Singleton 的最佳方法
 */
public class Elvis2 {
    private static final Elvis2 INSTANCE = new Elvis2();

    private Elvis2() {
    }

    public static Elvis2 getInstance(){
        return INSTANCE;
    }

    public void leaveTheBuilding() {
    }

    private Object readResolve(){
        return INSTANCE;
    }
}
