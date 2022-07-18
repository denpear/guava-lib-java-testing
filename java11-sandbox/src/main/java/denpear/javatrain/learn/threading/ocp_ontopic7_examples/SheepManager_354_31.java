package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SheepManager_354_31 {
    private AtomicInteger sheepCount = new AtomicInteger(0); // primitive variable

    /**
     * пастухи бегают с разной скоростью,
     * что доложить о результате подсчета
     *
     * @param args - аргументы командной строки
     */
    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);
            SheepManager_354_31 manager = new SheepManager_354_31();
            for (int i = 0; i < 10; i++) {
                service.submit(() -> manager.incrementAndReport());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private void incrementAndReport() {
        System.out.print(sheepCount.incrementAndGet() + " "); //pre-increment from real object AtomicInteger
    }

}
