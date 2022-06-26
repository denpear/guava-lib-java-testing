package denpear.javatrain.learn.threading.ocp_ontopic_examples;

public class PrintData implements Runnable {
    public static void main(String[] args) {
        (new Thread(new PrintData())).start();
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

