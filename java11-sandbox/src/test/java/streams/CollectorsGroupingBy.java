package streams;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsGroupingBy {

    public static void print(Object... args) {
        // Arrays.stream(args).forEach(System.out::println);
        Arrays.stream(args).forEach(x -> System.out.println(x)); // is the Lambda equivalent of System.out::println But then Warning:(27, 42) Lambda can be replaced with method reference
    }


    @Test
    public void usingGroupingByCollector() {
        // Got Map<Integer,List<String>>

        Stream<String> ohMy4 = Stream.of("lions", "tigers", "bears", "denis", "dennis");

        Map<Integer, List<String>> map4 = ohMy4.collect(Collectors.groupingBy(String::length));
        print(map4);

        //Got Map<Integer,Set<String>>

        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears", "denis", "dennis");

        Map<Integer, Set<String>> map3 = ohMy3.collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        print(map3);

        //Got TreeMap<Integer, Set<String>>
        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears", "denis", "dennis");

        TreeMap<Integer, Set<String>> map2 = ohMy2.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
        print(map2, map2.getClass().toString());

        Stream<String> ohMy = Stream.of("lions", "tigers", "bears", "denis", "dennis");

        TreeMap<Integer, List<String>> map = ohMy.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
        print(map, map.getClass().getGenericSuperclass());
    }

}
