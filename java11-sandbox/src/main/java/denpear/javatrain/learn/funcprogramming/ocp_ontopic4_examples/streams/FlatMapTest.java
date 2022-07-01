package denpear.javatrain.learn.funcprogramming.ocp_ontopic4_examples.streams;

import java.util.Arrays;
import java.util.stream.Stream;

public class FlatMapTest {

    public static void main(String[] args) {
        String[] d = Stream.of("H e l l o", "W o r l d!").flatMap((p) -> Arrays.stream(p.split(" "))).toArray(String[]::new);
        System.out.println(d.toString());
    }

}
