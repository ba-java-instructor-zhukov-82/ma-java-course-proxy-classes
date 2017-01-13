package labs.work_1_9_2;

import labs.work_1_9_1.Calculate;
import labs.work_1_9_1.CalculateImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class CalculateBitwiseProxy implements InvocationHandler {

    private Class[] interfaces;
    private Object[] delegates;

    private CalculateBitwiseProxy(Class[] interfaces, Object[] delegates) {
        this.interfaces = interfaces;
        this.delegates = delegates;
    }

    public static Object newInstance(Class obj) {
        Object[] delegates = new Object[] {new CalculateBitwiseImpl(), new CalculateImpl()};
        List<Class<?>> listInterfaces = Arrays.asList(CalculateBitwise.class, Calculate.class);
        if ( listInterfaces.contains(obj)) {
            Class[] proxyInterfaces = listInterfaces.toArray(new Class[listInterfaces.size()]);
            return Proxy.newProxyInstance(
                    obj.getClassLoader(),
                    proxyInterfaces,
                    new CalculateBitwiseProxy(proxyInterfaces, delegates));
        }
        System.out.println("Can not create object!");
        return null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class declaringClass = method.getDeclaringClass();
        for (int i=0; i<interfaces.length; i++) {
            if (declaringClass.isAssignableFrom(interfaces[i])) {
                return method.invoke(delegates[i], args);
            }
        }
        System.out.println("Method cannot be dispatched");
        return null;
    }
}
