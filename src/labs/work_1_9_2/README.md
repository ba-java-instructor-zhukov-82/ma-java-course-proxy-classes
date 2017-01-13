##Laboratory Work 1.9.2.  

Open project called  DemoProxy1 (from 1-9-1 lab) 

<ol>
    <li>Create a CalculateBitwise interface with methods: andBitwise() and 
        orBitwise() that perform a operation & (AND) and a operation | (OR)  
        of two numbers </li>
    <li>Create a CalculateBitwiseImpl class, which implements the interface 
        CalculateBitwise </li>
    <li>Rewrite a CalculateProxy class that it contains:  
        <ul>
            <li> a interfaces field as an array type Class for 
                 implemented interfaces; </li>
            <li> a delegates field as an array type  Object for 
                 target objects; </li>
            <li> a private constructor to initialize these fields; </li>
        </ul>
    </li>
    <li>Rewrite a newInstance() of CalculateProxy class, that receives 
        the interface class, creates an arrays of target objects and 
        implemented interfaces, and creates a proxy class instance, 
        if the specified interface is part of an array of interfaces 
        this the proxy class. </li> 
    <li>Rewrite a invoke() method of CalculateProxy class, 
        which determines which interface belongs a method and delegate 
        its performance to the appropriate object. </li>
    <li>Rewrite a main() method of Main class, which demonstrates 
        the create instance of a proxy class and the invoke of methods 
        different interfaces.</li>
</ol>

####Here is the solution code:  

CalculateBitwise.java
````java
public interface CalculateBitwise {

    int andBitwise(int a, int b);
    int orBitwise(int a, int b);
}
````

CalculateBitwiseImpl.java
```java
public class CalculateBitwiseImpl implements CalculateBitwise {

    @Override
    public int andBitwise(int a, int b) {
        return a & b;
    }

    @Override
    public int orBitwise(int a, int b) {
        return a | b;
    }
}
```

CalculateBitwiseProxy.java
```java
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
```

Work_1_9_2_Main.java
```java
public class Work_1_9_2_Main {

    public static void main(String[] args) {

        solution_1_9_2_Test();
    }

    private static void solution_1_9_2_Test() {

        Calculate proxy = (Calculate) CalculateBitwiseProxy.newInstance(Calculate.class);
        if (proxy != null) {
            int num_1 = 100, num_2 = 5;
            System.out.println("number_1 = " + num_1 + "; number_2 = " + num_2);
            System.out.println("1) mult -> " + proxy.multiplication(num_1, num_2));
            System.out.println("2) div -> " + proxy.division(num_1, num_2));
        }

        System.out.println("-------------------------------");
        CalculateBitwise bitwiseProxy = (CalculateBitwise) proxy;
        if (bitwiseProxy != null) {
            int number_1 = 15, number_2 = 35;
            System.out.println("number_1 = " + number_1 + "; number_2 = " + number_2);
            System.out.println("3) or -> " + bitwiseProxy.orBitwise(number_1, number_2));
            System.out.println("4) and -> " + bitwiseProxy.andBitwise(number_1, number_2));
        }
    }
}
```

