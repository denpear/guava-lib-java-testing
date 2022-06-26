package denpear.javatrain.learn.funcprogramming.ocp_ontopic4_examples;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BiConsumer_176 {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        BiConsumer<String, Integer> bic1 = map::put;
        BiConsumer<String, Integer> bic2 = (k, v) -> map.put(k, v);

        bic1.accept("Chiken", 7);
        bic2.accept("chik", 4);
        System.out.print(map);

    }
}
