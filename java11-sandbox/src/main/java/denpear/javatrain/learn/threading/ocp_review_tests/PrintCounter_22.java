package denpear.javatrain.learn.threading.ocp_review_tests;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class PrintCounter_22 {
    static int counter = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<Future<?>> results = new ArrayList<>();
        IntStream.iterate(0, i -> i + 1).limit(5);
        //NOT COMPILE:: .forEach(i -> results.add(executorService.execute(() -> counter++))); // n1
        for (Future<?> result : results) {
            System.out.print(result.get() + " "); //n2
        }
        executorService.shutdown();
    }
}
