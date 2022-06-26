package denpear.javatrain.learn.threading.ocp_ontopic_examples;

public class CalculateAverage_331 implements Runnable {
    private double[] scores;

    public CalculateAverage_331(double[] scores) {
        this.scores = scores;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

    }
}
