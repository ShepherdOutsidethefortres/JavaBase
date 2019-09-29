package demos;

public class DeepCopy {

    /**
     * String 类型很特殊，它是不可变类型，即一旦初始化后，就不可以改变。因为他为引用型，而且他指向的值为常量，克隆出来的对象改变他的值
     * 实际上是改变了克隆出来对象String类型成员的指向，不会影响被克隆对象的。
     * 解释：如果原来对象的一个string变量进行初始化的时候，指向的是一个字符串常量，该字符串常量会被放到常量池中，
     * 该string类型的引用将会指向该常量。进行克隆后，得到一个新的对象，如果该对象的string变量重新赋值，那么只会
     * 有这个string 引用变量指向一个新的内存区域，对原来对象中的string变量不会有影响。
     *
     * 深克隆，如果包含子对象，必须子对象也实现Cloneable接口，重写clone方法
     * 在父对象的clone方法中调用子类的clone方法完成拷贝动作，返回一个新的父对象
     *
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person("张三", "男", new Skill("会砍材", "会做饭"));

        Person p2 = (Person) p1.clone();

        //字符串对象的克隆
        System.out.println(p1.getName().hashCode());
        System.out.println(p2.getName().hashCode());

        //子类的克隆
        System.out.println(p1.getSkill().hashCode());
        System.out.println(p2.getSkill().hashCode());

        p2.setName("李四");

        //字符串对象修改
        System.out.println(p1.getName().hashCode());
        System.out.println(p2.getName().hashCode());

        //对象克隆成功
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());

        //输出对象
        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }
}
