package denpear.javatrain.learn.algorithms.merging;

import java.util.Arrays;

public class MergeTwoSortedArrays_3 {
    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        int length1 = arr1.length;
        int length2 = arr2.length;
        int[] mergedArr = new int[length1 + length2];
        int arr1Position, arr2Position, mergedPosition;
        arr1Position = arr2Position = mergedPosition = 0;

        while (arr1Position < length1 && arr2Position < length2) {
            if (arr1[arr1Position] < arr2[arr2Position]) {
                mergedArr[mergedPosition++] = arr1[arr1Position++];
            } else {
                mergedArr[mergedPosition++] = arr2[arr2Position++];
            }
        }

        // массив большего размера дописываем в конец
        while (arr1Position < length1) {
            mergedArr[mergedPosition++] = arr1[arr1Position++];
        }

        // массив большего размера дописываем в конец
        while (arr2Position < length2) {
            mergedArr[mergedPosition++] = arr2[arr2Position++];
        }

        return mergedArr;
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 6, 7};
        int[] arr2 = {1, 2, 3, 4, 6};

        System.out.println(Arrays.toString(mergeArrays(arr1, arr2)));

    }
}




