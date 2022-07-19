package denpear.javatrain.learn.algorithms.recursion;

public class CountUp {
    public static int countUp(int start, int end) {
        if (start == end) {
            System.out.print(start + "... ");
            return end;
        }
        System.out.print(start + "... ");
        return countUp(start + 1, end);
    }

    public static void main(String[] args) {
        countUp(1, 7);
    }
}
