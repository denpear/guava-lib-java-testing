package streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateIntStream {


    @Test
    public void fromIntStreamToStreamInteger() {
        //здесь мы боксируем за счет .boxed()
        Stream<Integer> stream = IntStream.range(0, 10).boxed();
    }


    @Test
    public void rangeInit_1() {
        //считаем сумму нечетных чисел
        //здесь мы боксируем при помощи mapToObj(Integer::valueOf), получая завернутые в объекты Ints
        List<Integer> listCollection = IntStream.range(0, 10).mapToObj(Integer::valueOf).collect(Collectors.toList());
        Integer sumOddOld = 0;
        for (Integer i : listCollection) {
            if (i % 2 != 0) {
                System.out.println("Нечет: " + i);
                sumOddOld += i;
            }
            System.out.println(i);
        }
        System.out.println(sumOddOld);
    }

    @Test

    public void fromStream() {
        //считаем сумму нечетных чисел
        //согласно сигнатуре reduce принимает бинарную операцию
        Integer sumOdd = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter(num -> num % 2 != 0).reduce((a, b) -> Integer.sum(a, b)).orElse(0);
        System.out.println(sumOdd);
    }

    @Test
    public void fromParallelStream() {
        //считаем сумму нечетных чисел
        Integer sumOdd = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10).parallel().filter(num -> num % 2 != 0).reduce(Integer::sum).orElse(0);
        System.out.println(sumOdd);
    }

    @Test
    public void fromIntStream() {
        IntStream.of(1, 2, 3);

        /*
        As you probably guessed from the resulting streams, the rangeClosed function includes the ending int, while range excludes it.
         */

        IntStream.range(1, 3);
        // > 1, 2
        IntStream.rangeClosed(1, 3);
        // > 1, 2, 3

        //можно обсчитать каждый элемент потока перед выводом
        IntStream.iterate(0, i -> i + 2).limit(10).forEach(System.out::println);


    }

    @Test
    public void fromIntStream_1() {
        //если генерить случайные числа из диапазона 1000
        IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1000)).limit(3).forEach(System.out::println);
    }

    @Test
    public void fromIntStream_2() {
        //получить поток из 10 четных чисел
        IntStream.iterate(0, i -> i + 1).filter(i -> i % 2 == 0).limit(10).forEach(System.out::println);
    }

    @Test
    public void fromIntStream_3() {
        //получить поток из 10 нечетных чисел
        IntStream.iterate(0, i -> i + 1).filter(i -> i % 2 != 0).limit(10).forEach(System.out::println);
    }


    @Test
    public void getMaxInt() {
        // найти максимальное число
        int anInt = IntStream.range(1, 5).max().getAsInt();
        System.out.println(anInt);
    }

    @Test
    public void getMinInt() {
        // найти минимальное число
        int anInt = IntStream.range(1, 5).min().getAsInt();
        System.out.println(anInt);
    }


}
