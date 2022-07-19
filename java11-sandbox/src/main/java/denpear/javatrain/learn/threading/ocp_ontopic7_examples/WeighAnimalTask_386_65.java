package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class WeighAnimalTask_386_65 extends RecursiveTask<Double> {

    private int start;
    private int end;
    private Double[] weigths;

    public WeighAnimalTask_386_65(Double[] weigths, int start, int end) {
        this.start = start;
        this.end = end;
        this.weigths = weigths;
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<Double> task = new WeighAnimalTask_386_65(weights, 0, weights.length); // инициализация, первый вызов
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
     * В этом примере текущий поток вызывает join(), заставляя его ждать завершения подзадачи [начало,середина],
     * прежде чем приступить к подзадаче [середина,конец]. Таким образом, результаты фактически выполняются в однопоточном режиме.
     * На экзамене убедитесь, что функция fork() вызывается перед тем, как текущий поток начинает выполнение подзадачи,
     * и что функция join() вызывается после того, как он завершает выполнение подзадачи для получения результатов,
     * чтобы они выполнялись параллельно.
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
            RecursiveTask<Double> otherTask = new WeighAnimalTask_386_65(weigths, start, middle);
            otherTask.fork().join();
            return new WeighAnimalTask_386_65(weigths, middle, end).compute();
        }
    }
}
