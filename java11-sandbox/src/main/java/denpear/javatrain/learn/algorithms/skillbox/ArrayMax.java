package denpear.javatrain.learn.algorithms.skillbox;

import java.util.Arrays;

/**
 * 1. решаем задачу сортировкой
 */

public class ArrayMax {
    public static void main(String[] args) {
        int[] ages = {10, 6, 15, 93, 43, 7, 32};
        Arrays.sort(ages);
        int maxAge = ages[ages.length - 1];
        System.out.println(maxAge);
    }
}


