package labs.work_1_9_1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Work_1_9_1_Main {

    public static void main(String[] args) {
        solution_1_9_1_Test();
    }

    private static void solution_1_9_1_Test() {
        Calculate calculate = new CalculateImpl();

        Calculate proxy = (Calculate) Proxy.newProxyInstance(
                calculate.getClass().getClassLoader(),
                calculate.getClass().getInterfaces(),
                (InvocationHandler) CalculateProxy.newInstance(calculate));

        System.out.println("\t\tMethods calling, using the proxy:");

        System.out.println(proxy.multiplication(20, 5));
        System.out.println(proxy.division(20, 5));
    }
}
