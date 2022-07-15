package denpear.javatrain.learn.algorithms.merging;

import java.util.Arrays;

/**
 * Нужно слить 2отсортированных массива в один отсортированный массив
 * Вход: 2 отсортированных (по возрастанию) массива A и B длины M и N
 * Выход: Отсортированный (по возрастанию) массив, состоящий из элементов первых двух
 * Ввод [1, 2, 5], [1, 2, 3, 4, 6]
 * Вывод [1, 1, 2, 2, 3, 4, 5, 6]
 */

public class MergeTwoSortedArrays {

    static int[] arrayA = new int[]{1, 2, 5};
    static int[] arrayB = new int[]{1, 2, 3, 4, 6};
    static int k = 0;
    static int[] arrayR = new int[arrayA.length + arrayB.length];

    public static void main(String[] args) {
        for (int i = 0; i < arrayA.length; i++) {

            for (int j = 0; j < arrayB.length; j++) {

                if (arrayA[i] == arrayB[j]) {
                    arrayR[k] = arrayA[i];
                    k++;
                    i++;
                    arrayR[k] = arrayB[j];
                    k++;
                } else if (arrayA[i] > arrayB[j]) {
                    arrayR[k] = arrayB[j];
                    k++;
                } else if (arrayA[i] < arrayB[j]) {
                    arrayR[k] = arrayA[i];
                    k++;
                    if (i + 1 == arrayA.length) {
                        arrayR[k] = arrayB[j];
                    }
                }
            }
            System.out.println(Arrays.toString(arrayR));
        }
    }
}



