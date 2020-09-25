package zhw.dynamicproxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler {

    private EletricCar car;

    public InvocationHandlerImpl(EletricCar car) {
        this.car = car;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("You are going to invoke " + method.getName() + " ...");
        method.invoke(proxy, null);
        System.out.println(method.getName() + " invocation Has Been finished...");
        return null;
    }
}
