package streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReduceTests {

    @Test
    public void Reduce_1() {
        //считаем сумму нечетных чисел
        List<Integer> listCollection = IntStream.range(0, 10).mapToObj(Integer::valueOf).collect(Collectors.toList());
        Integer sumOddOld = 0;
        for (Integer i : listCollection) {
            if (i % 2 != 0) {
                sumOddOld += i;
            }
            System.out.println(i);
        }
        System.out.println(sumOddOld);
    }
}
