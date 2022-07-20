package denpear.javatrain.learn.algorithms.sorting;

import java.util.Arrays;

/**
 * Название	            Лучшее время	Среднее	    Худшее	    Память	Устойчивость	Обмены (в среднем)
 * "Сортировка кучей
 * (Heap Sort)"	            O(nlogn)	O(nlogn)	O(nlogn)	O(1)	 Нет	            O(nlogn)
 * Описание:
 * Строим из массива кучу, по очереди извлекаем минимум кучи.
 */
public class HeapSort {
    /**
     * Для понимания работы пирамидального алгоритма сортировки нужно понять структуру, на которой он основан – пирамиду.
     * <p>
     * Пирамида или двоичная куча – это дерево, в котором каждый узел состоит в отношениях с дочерними узлами.
     * Добавление нового узла начинается с левой позиции нижнего неполного уровня.
     *
     * @param array
     */
    public static void heapSort(int[] array) {
        if (array.length == 0) return;

        // Строим кучу
        int length = array.length;
        // проходим от первого без ответвлений к корню
        for (int i = length / 2 - 1; i >= 0; i--)
            heapify(array, length, i);
        // срезаем вершину: самый правый элемент выталкиваем на вершину, а самый левый становиться самым правым и выходит из игры
        for (int i = length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i]; //самый левый элемент
            array[i] = temp; //меняем на самый правый

            heapify(array, i, 0);
        }
        System.out.println("После сортировки: " + Arrays.toString(array));
    }

    static void heapify(int[] array, int length, int i) {
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        int largest = i;

        // если левый дочерний больше родительского
        if (leftChild < length && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        // если правый дочерний больше родительского
        if (rightChild < length && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        // если должна произойти замена
        if (largest != i) { // базовый случай
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapify(array, length, largest);
        }
    }


    public static void main(String[] args) {
        int[] ints = {6, 1, 8, 3, 5, 2, 4};
        //  int[] ints = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1000)).limit(6).toArray();
        System.out.println("До сортировки: " + Arrays.toString(ints));
        HeapSort.heapSort(ints);
    }

}
