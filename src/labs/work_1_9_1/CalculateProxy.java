package labs.work_1_9_1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class CalculateProxy implements InvocationHandler {

    private static CalculateProxy proxy;

    Object objCalc;

    private CalculateProxy(Object objCalc) {
        this.objCalc = objCalc;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("The method <" + method.getName() +
                "> invoked with arg:" + Arrays.toString(args));

        Object result = method.invoke(objCalc, args);

        System.out.println("The method <" + method.getName() +
                "> ends with result:" + result.toString());

        return result;
    }

    public static Object newInstance(Object obj) {
        proxy = new CalculateProxy(obj);
        return proxy;
    }
}
