package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

public class PrintData_332_8 implements Runnable {
    public static void main(String[] args) {
        (new Thread(new PrintData_332_8())).start();
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
        for (int i = 0; i < 3; i++) {
            System.out.println("Printing record " + i);
        }
    }
}

