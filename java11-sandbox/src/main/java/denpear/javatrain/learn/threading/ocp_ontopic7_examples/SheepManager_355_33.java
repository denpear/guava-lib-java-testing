package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SheepManager_355_33 {
    private int sheepCount = 0; // primitive variable

    /**
     * пастухи бегают с разной скоростью,
     * что доложить о результате подсчета
     * Хотя все потоки по-прежнему создаются и выполняются одновременно, каждый из них ждет в синхронизированном блоке,
     * пока рабочий не выполнит инкремент и не сообщит результат, прежде чем вступить в работу.
     *
     * @param args - аргументы командной строки
     */
    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);
            SheepManager_355_33 manager = new SheepManager_355_33();
            for (int i = 0; i < 10; i++) {
                service.submit(() -> manager.incrementAndReport());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    /**
     * Хотя все потоки по-прежнему создаются и выполняются одновременно, каждый из них ждет в синхронизированном блоке,
     * пока рабочий не выполнит инкремент и не сообщит результат, прежде чем вступить в работу.
     */
    private void incrementAndReport() {
        synchronized (this) {
            System.out.print((++sheepCount) + " "); //pre-increment
        }
    }
}
