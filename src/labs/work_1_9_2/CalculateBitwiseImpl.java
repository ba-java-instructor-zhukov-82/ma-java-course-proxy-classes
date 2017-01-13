package labs.work_1_9_2;

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
