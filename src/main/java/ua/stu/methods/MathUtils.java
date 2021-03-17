package ua.stu.methods;

public class MathUtils {

    public static int greatestCommonDivisor(int a, int length){
        return length == 0 ? a : greatestCommonDivisor(length, a % length);
    }
}
