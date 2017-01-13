package presentation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Handler implements InvocationHandler {

    private Object target;

    public Handler(Object target) {

        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("The method <" + method.getName() +
                           "> invoked with arg:" + Arrays.toString(args));

        Object result = method.invoke(target, args);

        System.out.println("The method <" + method.getName() +
                           "> ends with result:" + result.toString());

        return result;
    }
}
