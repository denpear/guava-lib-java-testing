package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LionPenManager_378_57 {
    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            LionPenManager_378_57 manager_378_57 = new LionPenManager_378_57();
            for (int i = 0; i < 4; i++) service.submit(() -> manager_378_57.performTask());
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

    public void performTask() {
        removeAnimals();
        cleanPen();
        addAnimals();
    }

}
