package denpear.javatrain.learn.algorithms;

import org.junit.Test;

import java.util.Arrays;

import static guavagooglelibrary.GuavaCollectionsTest.print;
import static java.lang.System.out;

public class ArrayMaxTest {
    @Test
    public void findSmallestTransactionTest() {
        int transactions[] = new int[]{-1392, -1920, -7, -453, -91234};

        int minSum = 0;


        for (int i = 0; i < transactions.length; i++) {
            minSum = Math.min(minSum, transactions[i]);
        }
        out.printf("Min Sum = %d\n", minSum);
    }


    @Test
    public void findMaxBySort() {
        int[] ages = {10, 6, 15, 93, 43, 7, 32};
        Arrays.sort(ages);
        int maxAge = ages[ages.length - 1];
        System.out.println(maxAge);
    }

    @Test
    public void findMaxByCompare() {
        int ageMom = 45;
        int ageDad = 47;
        int maxAgeMomandDad = Math.max(ageDad, ageMom);
        int ageGrandma = 83;
        int maxAge = Math.max(maxAgeMomandDad, ageGrandma);
        System.out.println(maxAge);
    }


    @Test
    public void findMaxByIterateCompare() {
        int[] ages = {10, 6, 15, 93, 43, 7, 32};
        int maxAge = 0;

        for (int i = 0; i < ages.length; i++) {
            maxAge = Math.max(maxAge, ages[i]);
        }
        System.out.println(maxAge);
    }


    @Test
    public void findMaxByDoubleMaxIterateCompare() {
        int[] ages = {10, 6, 15, 93, 43, 7, 32};
        int maxAge = 0;

        for (int i = 0; i < ages.length; i++) {
            maxAge = Math.max(maxAge, ages[i]);
        }

        int maxAges2 = 0;

        for (int i = 0; i < ages.length; i++) {
            if (ages[i] < maxAge) {
                maxAges2 = Math.max(maxAges2, ages[i]);
            }
        }
        print(maxAge, maxAges2);
    }


    @Test
    public void findMaxByExtremes() {
        int[] ages = {10, 6, 15, 93, 43, 7, 32};
        int[] top3ages = findTopElement(ages, 3);

        print(Arrays.toString(top3ages));
    }

    public static int findMaxOneBelowUpperBoundary(int[] inputArray, int topBoundary) {
        int currenMax = Integer.MIN_VALUE; // -2147483647 - min int
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] < topBoundary) {
                currenMax = Math.max(currenMax, inputArray[i]);
            }
        }
        return currenMax;
    }

    /**
     * Найти  отсортированные по убыванию самые большые элементы исходного массива
     *
     * @param inputArray      - исходный массив
     * @param numberofElemets - размер итогового результруюещего массива
     * @return возвращаем запрощеного размера упорядоченный результруюещий новый массив
     */

    public static int[] findTopElement(int[] inputArray, int numberofElemets) { // количество интераций это numberofElemets, т.е. 3
        int[] topElements = new int[numberofElemets];
        int previousMax = Integer.MAX_VALUE; // 2147483647 - max int

        for (int i = 0; i < numberofElemets; i++) {
            int currenMax = findMaxOneBelowUpperBoundary(inputArray, previousMax);
            previousMax = currenMax;
            topElements[i] = currenMax; // присваиваем элементу с индексом конкретное значение
        }
        return topElements;
    }

}