package denpear.javatrain.learn.algorithms.sorting;

import java.util.Arrays;

public class MergeSort {
    static int mergeSortStatisticCount = 0;
    static int mergeStatisticCount = 0;

    /**
     * Название	                Лучшее время	Среднее	    Худшее	            Память	                    Устойчивость	Обмены (в среднем)
     * "Сортировка слиянием
     * (Merge Sort)"	            O(nlogn)	O(nlogn)	O(nlogn)	"O(n) (обычная реализация)
     * O(1) (модифицированная реализация)"	    Да	           O(nlogn)
     * <p>
     * Описание:
     * Алгоритм состоит в разделении массива пополам, сортировке половин и их слиянии.
     * https://proglib.io/p/java-sorting-algorithms
     * Примените теорему, и вы увидите, что в нашем случае a=b^k, ибо 2=2^1.
     * Значит, сложность равна O(nlog n), и это лучшая временная сложность для алгоритма сортировки.
     * Доказано, что массив не может быть отсортирован быстрее, чем O(nlog n).
     *
     * @param array
     * @param left
     * @param right
     */

    public static void mergeSort(int[] array, int left, int right) {
        if (right <= left) return; // условие выхода из рекурсии
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
        mergeSortStatisticCount++;
        System.out.println("Сортировка после возврата в mergeSort в " + mergeSortStatisticCount + " раз : " + Arrays.toString(array));
    }

    static void merge(int[] array, int left, int mid, int right) {
        // вычисляем длину
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        // создаем временные подмассивы
        int leftArray[] = new int[lengthLeft];
        int rightArray[] = new int[lengthRight];

        // копируем отсортированные массивы во временные
        for (int i = 0; i < lengthLeft; i++)
            leftArray[i] = array[left + i];
        for (int i = 0; i < lengthRight; i++)
            rightArray[i] = array[mid + i + 1];

        // итераторы содержат текущий индекс временного подмассива
        int leftIndex = 0;
        int rightIndex = 0;

        // копируем из leftArray и rightArray обратно в массив
        for (int i = left; i < right + 1; i++) {
            // если остаются нескопированные элементы в R и L, копируем минимальный
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }
            // если все элементы были скопированы из rightArray, скопировать остальные из leftArray
            else if (leftIndex < lengthLeft) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            }
            // если все элементы были скопированы из leftArray, скопировать остальные из rightArray
            else if (rightIndex < lengthRight) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
        mergeStatisticCount++;
        System.out.println("Сортировка после завершения работы merge для leftArray в " + mergeStatisticCount + " раз : " + Arrays.toString(leftArray));
        System.out.println("Сортировка после завершения работы merge для rightArray в " + mergeStatisticCount + " раз : " + Arrays.toString(rightArray));
    }

    public static void main(String[] args) {
        int[] ints = {3, 5, 4, 2, 1};
        //int[] ints = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1000)).limit(5).toArray();
        System.out.println("До сортировки: " + Arrays.toString(ints));
        MergeSort.mergeSort(ints, 0, ints.length - 1);
    }

}
