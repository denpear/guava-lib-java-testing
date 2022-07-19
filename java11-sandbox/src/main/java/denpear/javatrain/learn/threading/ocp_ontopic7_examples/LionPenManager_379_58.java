package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LionPenManager_379_58 {
    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            LionPenManager_379_58 manager_378_57 = new LionPenManager_379_58();
            CyclicBarrier c1 = new CyclicBarrier(4);
            CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen Cleaned!"));
            for (int i = 0; i < 4; i++) service.submit(() -> manager_378_57.performTask(c1, c2));
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private void removeAnimals() {
        System.out.println("Removing animals");
    }

    private void cleanPen() {
        System.out.println("Cleaning the pen");
    }

    private void addAnimals() {
        System.out.println("Adding animals");
    }

    public void performTask(CyclicBarrier cyclicBarrier1, CyclicBarrier cyclicBarrier2) {

        try {
            removeAnimals();
            cyclicBarrier1.await();
            cleanPen();
            cyclicBarrier2.await();
            addAnimals();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
