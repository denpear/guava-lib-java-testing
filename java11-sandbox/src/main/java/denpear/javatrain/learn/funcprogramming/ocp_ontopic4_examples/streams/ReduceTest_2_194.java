package denpear.javatrain.learn.funcprogramming.ocp_ontopic4_examples.streams;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class ReduceTest_2_194 {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(3, 5, 6);
        System.out.println(stream.reduce(1, (a, b) -> a * b));
        // опуская identity, получаем сигнатру с Optional, а именно:
        // Optional<T> reduce(BinaryOperator<T> accumulator)

        BinaryOperator<Integer> op = (a, b) -> a * b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> treeElements = Stream.of(3, 5, 6);
        empty.reduce(op).ifPresent(System.out::print); //no output
        oneElement.reduce(op).ifPresent(System.out::println); //3
        treeElements.reduce(op).ifPresent(System.out::print); //90

    }
}
