import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OCPJavaSE8Test {


    public static void print(Object... args) {
        // Arrays.stream(args).forEach(System.out::println);
        Arrays.stream(args).forEach(x -> System.out.println(x)); // is the Lambda equivalent of System.out::println But then Warning:(27, 42) Lambda can be replaced with method reference
    }

    /**
     * We load the Supplier as a functional interface and can therefore
     * be used as the assignment target for a lambda expression
     * or method reference.
     */

    @Test
    public void printLambdaSupplier() {
        Supplier<Integer> random = () -> new Random().nextInt();
        print(random);
    }

    @Test
    public void usingOptionalWithPrimitiveStreams (){
        IntStream stream = IntStream.rangeClosed(1,10);// arithmetic progression (10+1)*10/2 = 11*5 = 55
        OptionalDouble optional = stream.average(); // divide on 10
        //print(optional.orElseThrow(IllegalStateException::new));
        print(optional.orElseThrow(() -> new IllegalStateException())); //5.5, but if it was NULL, an IllegalStateException() would be thrown
    }

    @Test
    public void collectingUsingBasicCollectors(){
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        String result = ohMy.collect(Collectors.joining("; "));
        print(result); // lions; tigers; bears

        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
        Double result2 = ohMy2.collect(Collectors.averagingInt(String::length));
        print(result2); // 5.333333333333333

        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
        TreeSet<String> result3 = ohMy3.filter(s -> s.startsWith("t"))
                .collect(Collectors.toCollection(TreeSet::new));
        print(result3); // [tigers]
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
    public void intoMapsCollecting(){
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        Map<String,Integer> map = ohMy.collect(Collectors.toMap(s -> s,String::length)); // Function.identity() = (s -> s)
        print(map);

        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
        Map<String,Integer> map2 = ohMy2.collect(Collectors.toMap(Function.identity(),String::length)); // s -> s is the same
        print(map2);

        /**
         * Прикольная сигнатура у метода в 3 параметра
         *     public static <T, K, U>
         *     Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
         *                                     Function<? super T, ? extends U> valueMapper,
         *                                     BinaryOperator<U> mergeFunction)
         */

        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears", "denis","dennis");
        Map <Integer,String> map3 = ohMy3.collect(Collectors.toMap(String::length,k -> k,(s1,s2) -> s1 + ";" + s2));
        print(map3);


        /**
         * Прикольная сигнатура у метода в 4 параметра
         *     public static <T, K, U, M extends Map<K, U>>
         *     Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper,
         *                              Function<? super T, ? extends U> valueMapper,
         *                              BinaryOperator<U> mergeFunction,
         *                              Supplier<M> mapFactory)
         */
        //Map <Integer,String>
        Stream<String> ohMy4 = Stream.of("lions", "tigers", "bears", "denis","dennis");
        Map <Integer,String> map4 = ohMy4.collect(Collectors.toMap(String::length,k -> k,(s1,s2) -> s1 + ";" + s2, TreeMap::new));
        print(map4);

    }


    @Test
    public void usingGroupingCollecting(){
        // Got Map<Integer,List<String>>

        Stream<String> ohMy4 = Stream.of("lions", "tigers", "bears", "denis","dennis");

        Map<Integer,List<String>> map4 = ohMy4.collect(Collectors.groupingBy(String::length));
        print(map4);

        //Got Map<Integer,Set<String>>

        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears", "denis","dennis");

        Map<Integer,Set<String>> map3 = ohMy3.collect(Collectors.groupingBy(String::length,Collectors.toSet()));
        print(map3);

        //Got TreeMap<Integer, Set<String>>
        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears", "denis","dennis");

        TreeMap<Integer, Set<String>> map2 = ohMy2.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
        print(map2, map2.getClass().toString());

        Stream<String> ohMy = Stream.of("lions", "tigers", "bears", "denis","dennis");

        TreeMap<Integer, List<String>> map = ohMy.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
        print(map, map.getClass().getGenericSuperclass());
    }

    /**
     * Partitioning is a special case of grouping. With partitioning, there are only two possible
     * groups—true and false. Partitioning is like splitting a list into two parts.
     * Разделение - это особый случай группировки.
     * При разделении есть только две возможные группы - истинная и ложная.
     * Разбиение на разделы похоже на разделение списка на две части.
     */

    @Test
    public void usingPartitioningCollecting(){
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map = ohMy.collect(
                Collectors.partitioningBy(s -> s.length() <= 7));
        System.out.println(map); // {false=[tigers], true=[lions, bears]}
    }


    @Test
    public void isStringHasSubstring(){
        String s = "(литерал в скобках)";
        Pattern p = Pattern.compile("(\\(.*\\))");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        String is = b ? "Верно!" : "Не верно!";
        print(is);

        if (b) {print("Содержит открывающию круглую скобку");
            if (s.contains(")")) { {print("Содержит закрывающию круглую скобку");}
        }
        }
    }

    @Test
    public void usingConversionChars(){
        String s = "(литерал в скобках)";

        s.chars()
                .mapToObj(i -> (char)i)
                .forEach(System.out::println);
    }


}
