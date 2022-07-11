package streams;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FindDuplicateInt {

    @Test
    public void collections_Frequency_Int() {
        List<Integer> numbers1 = IntStream.range(0, 10).mapToObj(Integer::valueOf).collect(Collectors.toList());

        List<Integer> numbers = Stream.of(0, 1, 1, 3, 2, 2, 2, 5, 5, 6, 7, 7, 8, 8, 8, 8, 9, 4, 4, 4, 4, 5, 8, 8, 9).collect(Collectors.toList());

        numbers.stream().filter(integer -> Collections.frequency(numbers, integer) > 1).collect(Collectors.toSet()).forEach(System.out::println);

    }

    @Test
    public void collections_Frequency_Int_2() {
        List<Integer> numbers = Stream.of(0, 1, 1, 3, 2, 2, 2, 5, 5, 6, 7, 7, 8, 8, 8, 8, 9, 4, 4, 4, 4, 5, 8, 8, 9).collect(Collectors.toList());
        //просто выбираем и подсчитываем повторения
        numbers.stream().collect(Collectors.groupingBy(integer -> integer, Collectors.counting()));

        Map<?, ?> treemap = numbers.stream().collect(Collectors.groupingBy(integer -> integer, Collectors.counting()));

        System.out.println(treemap);
    }

}
