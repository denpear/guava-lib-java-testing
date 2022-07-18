package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SheepManager_356_34 {
    /**
     * Хотя все потоки по-прежнему создаются и выполняются одновременно, каждый из них ждет в синхронизированном блоке,
     * пока рабочий не выполнит инкремент и не сообщит результат, прежде чем вступить в работу.
     * Мы могли бы синхронизироваться на любом объекте, лишь бы это был один и тот же объект. Хотя нам не нужно было делать переменную lock конечной,
     * это гарантирует, что она не будет переназначения после того, как потоки начнут ее использовать.
     */
    private final Object lock = new Object();
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
            SheepManager_356_34 manager = new SheepManager_356_34();
            for (int i = 0; i < 10; i++) {
                service.submit(() -> manager.incrementAndReport());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private void incrementAndReport() {
        synchronized (lock) {
            System.out.print((++sheepCount) + " "); //pre-increment
        }
    }
}
