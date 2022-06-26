package denpear.javatrain.learn.threading.ocp_ontopic_examples;
//+Future<?>

import java.util.concurrent.*;

public class CheckResults_3_341 {
    private static int counter = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            Future<?> result = executorService.submit(() -> {
                for (int i = 0; i < 500; i++) {
                    CheckResults_3_341.counter++;
                }
            });
            result.get(10, TimeUnit.SECONDS);
            System.out.println("Reached!");
        } catch (TimeoutException e) {
            System.out.println("Not reached in time");
        } finally {
            if (executorService != null) executorService.shutdown();
        }
    }
}
