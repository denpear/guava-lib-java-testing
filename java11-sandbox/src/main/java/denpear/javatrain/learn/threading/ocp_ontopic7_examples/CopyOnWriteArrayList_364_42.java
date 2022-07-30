package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Несмотря на добавление элементов в массив во время итерации по нему,
 * доступны только те элементы в коллекции, которые были на момент создания цикла for().
 * В качестве альтернативы, если бы мы использовали обычный объект ArrayList,
 * то во время выполнения возникло бы исключение ConcurrentModificationException во время выполнения.
 */
public class CopyOnWriteArrayList_364_42 {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
        for (Integer item : list) {
            System.out.print(item + " ");
            list.add(9);
        }
        System.out.println();
        System.out.println("Size: " + list.size());
        System.out.println("all array has values: ");
        list.stream().forEach(System.out::println);
    }
}
