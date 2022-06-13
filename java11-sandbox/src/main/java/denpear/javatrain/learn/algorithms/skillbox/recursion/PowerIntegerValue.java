package denpear.javatrain.learn.algorithms.skillbox.recursion;

public class PowerIntegerValue {
    /* рекурсивное возведение в степень */
    public static int pow(int value, int powValue) {
        if (powValue == 1) {
            return value;
        } else {
            return value * pow(value, powValue - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(pow(3, 4));
    }
}
