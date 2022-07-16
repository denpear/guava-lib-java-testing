package denpear.javatrain.learn.algorithms.sorting;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Название	            Лучшее время	Среднее	Худшее	Память	Устойчивость	Обмены (в среднем)
 * "Сортировка выбором
 * (Selection Sort)"	      O(n^2)	O(n^2)	O(n^2)	O(1)	     Нет	    O(n)
 * Описание:
 * На i-ом шаге алгоритма находим минимальный среди последних n−i+1, и меняем его местами с i-ым элементом в массиве.
 * <p>
 * Сортировка выбором тоже разделяет массив на сортированный и несортированный подмассивы.
 * Но на этот раз сортированный подмассив формируется вставкой минимального элемента
 * не отсортированного подмассива в конец сортированного, заменой:
 * <p>
 * 3 5 1 2 4
 * 1 5 3 2 4
 * 1 2 3 5 4
 * 1 2 3 5 4
 * 1 2 3 4 5
 * 1 2 3 4 5
 * Реализация
 * В каждой итерации вы предполагаете, что первый неотсортированный элемент минимален
 * и итерируете по всем оставшимся элементам в поисках меньшего.
 * <p>
 * После нахождения текущего минимума неотсортированной части массива вы меняете его местами с первым элементом,
 * и он уже часть отсортированного массива:
 */
public class SelectionSort {
    /**
     * Временная сложность
     * При поиске минимума для длины массива проверяются все элементы, поэтому сложность равна O(n).
     * Поиск минимума для каждого элемента массива равен O(n^2).
     *
     * @param array
     */
    public static void selectionSort(int[] array) {
        System.out.println("До сортировки: " + Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int minId = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j]; // перебором находим минимальный элемент
                    minId = j; // записываем его позицию в minId
                }
            }
            // замена, когда дошли до крайнего правого, даже осортировнный уже перезаписывает temp'ом, т.е. неустойчивый
            int temp = array[i];
            array[i] = min;
            array[minId] = temp;
        }
        System.out.println("После сортировки: " + Arrays.toString(array));
    }


    public static void main(String[] args) {
        int[] ints = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1000)).limit(6).toArray();
        SelectionSort.selectionSort(ints);
    }

}
