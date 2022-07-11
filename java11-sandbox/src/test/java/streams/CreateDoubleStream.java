package streams;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class CreateDoubleStream {


    @Test
    public void fromIntStreamToDoubleStream() {
        //если генерить случайные числа из диапазона 1000, а при помощи mapToDouble(i -> i + 0.25) прибавляем по 25 копеек
        IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1000)).limit(3).mapToDouble(i -> i + 0.25).forEach(System.out::println);
    }


}
