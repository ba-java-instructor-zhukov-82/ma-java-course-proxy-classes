##Laboratory Work 1.9.1.  

Create new project called DemoProxy1. Add package com.brainacad.demoproxy1. 

<ol>

<li>Create a Calculate interface with methods: multiplication() and 
   division() that perform a multiplication and a division of two numbers. </li>
<li>Create a CalculateImpl class, which implements the interface Calculate. </li>
<li>Create a CalculateProxy class, which is used as a invocation handler, 
   and includes:  
   <ul>
        <li> a objCalc field of type Object and a private constructor to 
             initialize (contains target object); </li>
        <li> overridden invoke() method, which outputs information about that 
             method is called (name), with what arguments, and what was the 
             result, and dispatches the execution target object; </li>
        <li> static method Object newInstance (Object obj), which receives the 
             target object and creates a proxy class instance; </li>
   </ul>
</li>
<li>Create a Main class with a main() method, which demonstrates 
    the calculation of a proxy class. </li>
   
</ol>

####Here is the solution code:  

Calculate.java
````java
public interface Calculate {

    int multiplication(int a, int b);
    int division(int a, int b);
}
````

CalculateImpl.java
````java
public class CalculateImpl implements Calculate {


    @Override
    public int multiplication(int a, int b) {
        return a * b;
    }

    @Override
    public int division(int a, int b) {
        return a / b;
    }
}
````

CalculateProxy.java
````java
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
````

Work_1_9_1_Main.java
```java
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
```