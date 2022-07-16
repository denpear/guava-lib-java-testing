package denpear.javatrain.learn.algorithms.sorting;

import java.util.Arrays;

/**
 * Название	            Лучшее время	Среднее	Худшее	Память	Устойчивость	Обмены (в среднем)
 * "Сортировка подсчетом
 * (Counting Sort)"	         O(n)	     O(n+k)	O(k)	  O(k)	Да	            O(n+k)
 * <p>
 * Описание:
 * Сортировка целых чисел, входящих в какой-то небольшой диапазон.
 * Создаем массив длины диапазона k, каждый элемент которого будет показывать,
 * сколько исходных элементов равны данному.
 * Бежим по массиву и считаем количество вхождений каждого числа.
 */
public class CountingSort {

    public static void countingSort(int[] sourceArray, int maxValue) {

        int[] redundantCountingArray = new int[maxValue]; // массив подсчета количества повторений
        for (int e = 0; e < sourceArray.length; e++) { //O(n)
            redundantCountingArray[sourceArray[e]]++; //сколько раз возникает каждое число, все остальные ячейки пусты, а номер элемента равен индексу в redundantCountingArray массиве с длиной k
        }
        int pointer = 0;//redundantCountingArray - уже упорядочен, но в нем масса нулевых промежутков
        for (int e = 0; e < redundantCountingArray.length; e++) { //O(k)
            // в случае, если повторения для индекса 6 в redundantCountingArray значение будет 2, т.е. redundantCountingArray[e] == 2
            for (int repeat = 0; repeat < redundantCountingArray[e]; repeat++) {
                sourceArray[pointer] = e;
                pointer++;
            }
        }
        System.out.println("После сортировки: " + Arrays.toString(sourceArray));
    }

    public static void main(String[] args) {
        int[] ints = {6, 4, 6, 8, 2, 7, 1}; // в случае, если повторения для индекса 6 в redundantCountingArray значение будет 2
        //int[] ints = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1000)).limit(6).toArray();
        System.out.println("До сортировки: " + Arrays.toString(ints));
        CountingSort.countingSort(ints, 1000);
    }

}
