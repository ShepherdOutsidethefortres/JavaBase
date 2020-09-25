package zhw.dynamicproxy;

import javassist.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/***
 * 动态代理，含有太多业务代码
 * 我们初衷是减少系统代码的冗余度，该方法增加了复杂度
 * 引出InvocationHandler角色
 * 代理模式中的代理Proxy角色，在执行代理业务的时候，无非是在调用真正业务之前或者之后做一些额外业务
 * 为了构造出具有通用性和简单性的代理类，可以将所有的触发真实角色动作交给一个触发的管理器，让这个管理器统一地管理触发。这种管理器就是Invocation Handler。
 * 动态代理工作的基本模式就是将自己的方法功能的实现交给 InvocationHandler角色，外界对Proxy角色中的每一个方法的调用，Proxy角色都会交给InvocationHandler来处理，而InvocationHandler则调用具体对象角色的方法。
 * 在这种模式之中：代理Proxy 和RealSubject 应该实现相同的功能，这一点相当重要。（我这里说的功能，可以理解为某个类的public方法）
 *
 * 在面向对象的编程之中，如果我们想要约定Proxy 和RealSubject可以实现相同的功能，有两种方式：
 *
 * 　　a.一个比较直观的方式，就是定义一个功能接口，然后让Proxy 和RealSubject来实现这个接口。
 *
 *    b.还有比较隐晦的方式，就是通过继承。因为如果Proxy 继承自RealSubject，这样Proxy则拥有了RealSubject的功能，
 *
 * 　　　Proxy还可以通过重写RealSubject中的方法，来实现多态。
 *
 * 其中JDK中提供的创建动态代理的机制，是以a 这种思路设计的，而cglib 则是以b思路设计的。
 */
public class StationDynamicProxy  {

    @Test
    public void createProxy() throws NotFoundException, CannotCompileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        ClassPool pool = ClassPool.getDefault();

        //代理类
        CtClass proxyClass = pool.makeClass("zhw.dynamicproxy.StationProxy");

        //代理类实现的接口
        CtClass proxyInterface = pool.get("zhw.dynamicproxy.TicketService");
        proxyClass.setInterfaces(new CtClass[]{proxyInterface});

        //代理类中的字段
        CtField proxyClassField = CtField.make("private zhw.dynamicproxy.Station station;", proxyClass);
        proxyClass.addField(proxyClassField);

        //设置构造函数
        CtClass stationClass = pool.get("zhw.dynamicproxy.Station");
        CtClass[] arrays = {stationClass};
        CtConstructor proxyConstructor = CtNewConstructor.make(arrays, null, CtNewConstructor.PASS_NONE, null, null, proxyClass);
        proxyConstructor.setBody("{this.station=$1;}");
        proxyClass.addConstructor(proxyConstructor);

        //创建收取手续 takeHandlingFee方法
        CtMethod takeHandlingFee = CtMethod.make("private void takeHandlingFee() {}", proxyClass);
        takeHandlingFee.setBody("System.out.println(\"收取手续费，打印发票。。。。。\");");
        proxyClass.addMethod(takeHandlingFee);

        //创建showAlertInfo 方法
        CtMethod showInfo = CtMethod.make("private void showAlertInfo(String info) {}", proxyClass);
        showInfo.setBody("System.out.println($1);");
        proxyClass.addMethod(showInfo);

        //sellTicket
        CtMethod sellTicket = CtMethod.make("public void sellTicket(){}", proxyClass);
        sellTicket.setBody("{this.showAlertInfo(\"××××您正在使用车票代售点进行购票，每张票将会收取5元手续费！××××\");"
                + "station.sellTicket();"
                + "this.takeHandlingFee();"
                + "this.showAlertInfo(\"××××欢迎您的光临，再见！××××\");}");
        proxyClass.addMethod(sellTicket);

        //添加inquire方法
        CtMethod inquire = CtMethod.make("public void inquire() {}", proxyClass);
        inquire.setBody("{this.showAlertInfo(\"××××欢迎光临本代售点，问询服务不会收取任何费用，本问询信息仅供参考，具体信息以车站真实数据为准！××××\");"
                + "station.inquire();"
                + "this.showAlertInfo(\"××××欢迎您的光临，再见！××××\");}"
        );
        proxyClass.addMethod(inquire);

        //添加widthraw方法
        CtMethod withdraw = CtMethod.make("public void withdraw() {}", proxyClass);
        withdraw.setBody("{this.showAlertInfo(\"××××欢迎光临本代售点，退票除了扣除票额的20%外，本代理处额外加收2元手续费！××××\");"
                + "station.withdraw();"
                + "this.takeHandlingFee();}"
        );
        proxyClass.addMethod(withdraw);

        //获取动态生成的class
        Class c = proxyClass.toClass();
        //获取构造器
        Constructor constructor= c.getConstructor(Station.class);
        //通过构造器实例化
        TicketService o = (TicketService)constructor.newInstance(new Station());
        o.inquire();

        proxyClass.writeFile("./output");
    }
}
