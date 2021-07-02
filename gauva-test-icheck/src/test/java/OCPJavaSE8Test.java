import org.junit.Test;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

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
}
