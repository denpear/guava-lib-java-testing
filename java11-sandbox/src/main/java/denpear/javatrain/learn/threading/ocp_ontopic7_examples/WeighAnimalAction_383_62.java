package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class WeighAnimalAction_383_62 extends RecursiveAction {

    private int start;
    private int end;
    private Double[] weigths;

    public WeighAnimalAction_383_62(Double[] weigths, int start, int end) {
        this.start = start;
        this.end = end;
        this.weigths = weigths;
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<?> task = new WeighAnimalAction_383_62(weights, 0, weights.length); // инициализация, первый вызов
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        //Print results
        System.out.println();
        System.out.println("Weights: ");
        Arrays.asList(weights).stream().forEach(d -> System.out.print(d.intValue() + " "));

    }

    /**
     * The main computation performed by this task.
     */
    @Override
    protected void compute() {

        if (end - start <= 3) // базовый случай, если > 3 животных, то делим задачу на две подзадачи
            for (int i = start; i < end; i++) {
                weigths[i] = (double) new Random().nextInt(100);
                System.out.println("Animal Weighed: " + i);
            }
        else { // рекурсивный случай: делим задачу на две подзадачи
            int middle = start + ((end - start) / 2);
            System.out.println("[start = " + start + ", middle = " + middle + ", end = " + end + "]");
            invokeAll(new WeighAnimalAction_383_62(weigths, start, middle), new WeighAnimalAction_383_62(weigths, middle, end));
        }
    }
}
