package denpear.javatrain.learn.funcprogramming.ocp_ontopic4_examples.streams;

import java.util.stream.Stream;

public class ReduceTest_1_193 {
    public static void main(String[] args) {
        // used:
        // T reduce(T identity, BinaryOperator<T> accumulator)
        Stream<String> stringStream = Stream.of("w", "o", "l", "f");
        String word = stringStream.reduce("", (s, c) -> s + c);
        System.out.println(word);
        // заменили лямбду на ссылочный метод
        Stream<String> stringStream2 = Stream.of("w", "o", "l", "f");
        word = stringStream2.reduce("a big ", String::concat);
        System.out.println(word);
    }
}
