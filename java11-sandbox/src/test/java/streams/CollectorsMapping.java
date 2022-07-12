package streams;

import org.junit.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class CollectorsMapping {


    @Test
    public void usingMappingCollector() {
        Stream<String> stream = Stream.of("lions", "tigers", "bears");
        Comparator<Character> charComparator = Comparator.naturalOrder();
        Map<Integer, Character> map = stream
                .collect(
                        groupingBy(
                                String::length,
                                mapping(
                                        s -> s.charAt(0),
                                        collectingAndThen(
                                                minBy(charComparator),
                                                Optional::get
                                        )
                                )
                        )
                );
        System.out.println(map);
    }

    @Test
    public void usingMappingCollector2() {
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> map = ohMy.collect(
                groupingBy(
                        String::length,
                        mapping(s -> s.charAt(0),
                                minBy(Comparator.naturalOrder())
                        )));
        System.out.println(map); // {5=Optional[b], 6=Optional[t]}
    }

}



