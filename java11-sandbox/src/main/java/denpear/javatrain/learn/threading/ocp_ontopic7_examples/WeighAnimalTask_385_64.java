package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class WeighAnimalTask_385_64 extends RecursiveTask<Double> {

    private int start;
    private int end;
    private Double[] weigths;

    public WeighAnimalTask_385_64(Double[] weigths, int start, int end) {
        this.start = start;
        this.end = end;
        this.weigths = weigths;
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<Double> task = new WeighAnimalTask_385_64(weights, 0, weights.length); // инициализация, первый вызов
        ForkJoinPool pool = new ForkJoinPool();
        Double sum = pool.invoke(task);
        System.out.println("Sum: " + sum);
        //Print results
        System.out.println();
        System.out.println("Weights: ");
        Arrays.asList(weights).stream().forEach(d -> System.out.print(d.intValue() + " "));

    }

    /**
     * The main computation performed by this task.
     */
    @Override
    protected Double compute() {

        if (end - start <= 3) { // базовый случай
            double sum = 0;
            for (int i = start; i < end; i++) {
                weigths[i] = (double) new Random().nextInt(100);
                System.out.println("Animal Weighed: " + i);
                sum += weigths[i];
            }
            return sum;
        } else { // рекурсивный случай
            int middle = start + ((end - start) / 2);
            System.out.println("[start = " + start + ", middle = " + middle + ", end = " + end + "]");
            RecursiveTask<Double> otherTask = new WeighAnimalTask_385_64(weigths, start, middle);
            otherTask.fork();
            return new WeighAnimalTask_385_64(weigths, middle, end).compute() + otherTask.join();
        }
    }
}
