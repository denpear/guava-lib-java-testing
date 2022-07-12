package streams;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsPartitioningBy {

    /**
     * Partitioning is a special case of grouping. With partitioning, there are only two possible
     * groups—true and false. Partitioning is like splitting a list into two parts.
     * Разделение - это особый случай группировки.
     * При разделении есть только две возможные группы - истинная и ложная.
     * Разбиение на разделы похоже на разделение списка на две части.
     */

    @Test
    public void usingPartitioningCollector() {
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map = ohMy.collect(
                Collectors.partitioningBy(s -> s.length() <= 7));
        System.out.println(map); // {false=[tigers], true=[lions, bears]}
    }
}
