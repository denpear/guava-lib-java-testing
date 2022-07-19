package denpear.javatrain.learn.algorithms.recursion;

public class CountDown {
    public static int countDown(int n) {
        if (n <= 1) {
            System.out.print(n + "... ");
            return 1;
        }
        System.out.print(n + "... ");
        return countDown(n - 1);
    }

    public static void main(String[] args) {
        countDown(7); // 7... 6... 5... 4... 3... 2... 1...
    }
}
