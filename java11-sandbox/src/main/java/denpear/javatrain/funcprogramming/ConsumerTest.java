package denpear.javatrain.funcprogramming;

import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String[] args) {
        Consumer<String> s1 = System.out::println;
        Consumer <String> s2 = x -> System.out.println(x);

        s1.accept("Denis");
        s1.accept("Grukhin");
    }
}
