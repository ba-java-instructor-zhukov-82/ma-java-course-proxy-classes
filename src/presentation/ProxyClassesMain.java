package presentation;

import java.lang.reflect.Proxy;

public class ProxyClassesMain {

    public static void main(String[] args) {
        plainCalculatorObjectTest();
        dynamicProxiedCalculatorObjectTest();
    }

    private static void plainCalculatorObjectTest() {

        Calculator plainCalculator = new CalculatorImpl();

        System.out.println("Call methods without proxy.");

        System.out.println(plainCalculator.add(2, 3));
        System.out.println(plainCalculator.mul(2, 3));
    }

    private static void dynamicProxiedCalculatorObjectTest() {

        Calculator plainCalculator = new CalculatorImpl();

        Calculator proxiedCalculator = (Calculator) Proxy.newProxyInstance(
                plainCalculator.getClass().getClassLoader(),
                plainCalculator.getClass().getInterfaces(),
                new Handler(plainCalculator));

        System.out.println("Call methods using the proxy.");

        System.out.println(proxiedCalculator.add(2, 3));
        System.out.println(proxiedCalculator.mul(2, 3));
    }
}
