package denpear.javatrain.learn.algorithms.skillbox.recursion;

public class BitsetPlusInnerClass {
    int[] bitData;

    BitsetPlusInnerClass(int n) {
        bitData = new int[n / 32 + 1]; // +1 для округления
    }


    int get(int bitIndex) {
        int x = bitData[bitIndex / 32];
        return (x >> (bitIndex % 32)) & 1;
    }

    void set(int bitIndex, int value) {
        if (get(bitIndex) == value) {
            return;
        }
        bitData[bitIndex / 32] ^= 1 << (bitIndex % 32);
    }

    public static class Main {

        static BitsetPlusInnerClass bitset;

        static void gen(int x, int n, int k) {
            if (n - x < k) {
                return;
            }
            if (x == n) {
                for (int i = 0; i < n; i++) {
                    System.out.println(bitset.get(i));
                }
                System.out.println();
                return;
            }
            bitset.set(x, 0);
            gen(x + 1, n, k);
            bitset.set(x, 1);
            gen(x + 1, n, k - 1);
        }

        public static void main(String[] args) {
            bitset = new BitsetPlusInnerClass(3);
            gen(0, 3, 2);
        }
    }

}