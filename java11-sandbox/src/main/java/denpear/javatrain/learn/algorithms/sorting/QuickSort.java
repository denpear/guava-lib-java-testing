package denpear.javatrain.learn.algorithms.sorting;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Название	                  Лучшее время	    Среднее	        Худшее	                    Память	                   Устойчивость	Обмены (в среднем)
 * "Быстрая сортировка
 * (Quick Sort)"	            O(nlogn)	    O(nlogn)	    "O(n^2)(маловероятно)"	 "O(logn)(стек вызовов)"	    Нет	             O(nlogn)
 */
public class QuickSort {
    /**
     * алгоритм техники «разделяй и властвуй». Он выбирает один элемент массива в качестве стержня
     * и сортирует остальные элементы вокруг (меньшие элементы налево, большие направо).
     * Так соблюдается правильная позиция самого «стержня». Затем алгоритм рекурсивно повторяет сортировку для правой и левой частей.
     *
     * @param array
     * @param begin
     * @param end
     */
    public static void quickSort(int[] array, int begin, int end) {
        if (end <= begin) return; // базовый случай
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
        System.out.println("После сортировки: " + Arrays.toString(array));
    }

    static int partition(int[] array, int begin, int end) {
        int pivot = end;

        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i] < array[pivot]) {
                int temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        int temp = array[pivot];
        array[pivot] = array[counter];
        array[counter] = temp;

        return counter;
    }


    public static void main(String[] args) {
        int[] ints = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1000)).limit(6).toArray();
        System.out.println("До сортировки: " + Arrays.toString(ints));
        QuickSort.quickSort(ints, 0, ints.length - 1);
    }

}
