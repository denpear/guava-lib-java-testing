package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SheepManager_350_27 {
    private int sheepCount = 0; // primitive variable

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
            SheepManager_350_27 manager = new SheepManager_350_27();
            for (int i = 0; i < 10; i++) {
                service.submit(() -> manager.incrementAndReport());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private void incrementAndReport() { //ср. с SheepManager_356_34_2 keyword synchronized
        System.out.print((++sheepCount) + " "); //pre-increment
    }

}
