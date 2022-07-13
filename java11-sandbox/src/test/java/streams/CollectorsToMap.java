package streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class CollectorsToMap {

    public static void print(Object... args) {
        // Arrays.stream(args).forEach(System.out::println);
        Arrays.stream(args).forEach(x -> System.out.println(x)); // is the Lambda equivalent of System.out::println But then Warning:(27, 42) Lambda can be replaced with method reference
    }

    /**
     * public static <T,K,U> Collector<T,?,Map<K,U>>
     * toMap(Function<? super T,extends K> keyMapper, Function<? super T, extends U> valueMapper, BinaryOperator<U> mergeFunction)
     * Parameters:
     * keyMapper - a mapping function to produce keys
     * valueMapper - a mapping function to produce values
     * mergeFunction - a merge function, used to resolve collisions between values associated with the same key,
     * as supplied to Map.merge(Object, Object, BiFunction)
     */

    @Test
    public void usingCollector_toMap_n_BinaryOperator() {
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Character> map = ohMy.collect(toMap(String::length, (String s) -> s.charAt(0), BinaryOperator.minBy(Comparator.naturalOrder())));
        System.out.println(map); // {5=b, 6=t}
    }

    /**
     * public static <T,K,U> Collector<T,?,Map<K,U>>
     * toMap(Function<? super T,extends K> keyMapper, Function<? super T, extends U> valueMapper, BinaryOperator<U> mergeFunction)
     * Parameters:
     * BinaryOperator как тернарный оператор (a, b) -> a > b ? b : a)
     */

    @Test
    public void usingCollector_toMap_BinaryOperator_as_ternary_operator() {
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Character> map = ohMy.collect(toMap(String::length, s -> s.charAt(0), (a, b) -> a > b ? b : a));
        System.out.println(map); // {5=b, 6=t}
    }


    /**
     * Обратите внимание, что предопределенные коллекторы находятся в классе Collectors, а не в классе Collector.
     * Это обычная тема, которую вы видели в разделе Collection vs. Collections.
     * Мы передаем предопределенный коллектор joining() в метод collect().
     * Затем все элементы потока объединяются в строку String с указанным разделителем между каждым элементом.
     * Очень важно передать коллектор в метод collect. Он существует для того, чтобы помогать собирать элементы.
     * Коллектор ничего не делает сам по себе.
     */

    @Test
    public void usingCollector_toMap() {
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> map = ohMy.collect(Collectors.toMap(s -> s, String::length)); // Function.identity() = (s -> s)
        print("Print map: " + map);

        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> map2 = ohMy2.collect(Collectors.toMap(Function.identity(), String::length)); // s -> s is the same
        print("Print map2: " + map2);

        /**
         * Прикольная сигнатура у метода в 3 параметра
         *     public static <T, K, U>
         *     Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
         *                                     Function<? super T, ? extends U> valueMapper,
         *                                     BinaryOperator<U> mergeFunction)
         */

        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears", "denis", "dennis");
        Map<Integer, String> map3 = ohMy3.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + ";" + s2));
        print("Print map3: " + map3);


        /**
         * Прикольная сигнатура у метода в 4 параметра
         *     public static <T, K, U, M extends Map<K, U>>
         *     Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper,
         *                              Function<? super T, ? extends U> valueMapper,
         *                              BinaryOperator<U> mergeFunction,
         *                              Supplier<M> mapFactory)
         */
        //Map <Integer,String>
        Stream<String> ohMy4 = Stream.of("lions", "tigers", "bears", "denis", "dennis");
        Map<Integer, String> map4 = ohMy4.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + ";" + s2, TreeMap::new));
        print("Print TreeMap map4: " + map4);

    }


}
