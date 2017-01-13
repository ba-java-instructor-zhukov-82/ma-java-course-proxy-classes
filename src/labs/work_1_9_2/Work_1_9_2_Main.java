package labs.work_1_9_2;

import labs.work_1_9_1.Calculate;

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
