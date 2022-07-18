package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

public class ReadInventoryThread_332_8 extends Thread {
    /**
     * В этом примере используется в общей сложности четыре потока - пользовательский поток main()
     * и три дополнительных потока, созданных методом main(). Хотя порядок выполнения потоков после их запуска является неопределенным,
     * порядок внутри одного потока остается линейным.
     * Например, цикл for() в PrintData_332_8 по-прежнему упорядочен, так же как и end, появляющийся после begin в методе main().
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("begin");
        (new ReadInventoryThread_332_8()).start();
        (new Thread(new PrintData_332_8())).start();
        (new ReadInventoryThread_332_8()).start();
        System.out.println("end");
    }

    /**
     * If this thread was constructed using a separate
     * {@code Runnable} run object, then that
     * {@code Runnable} object's {@code run} method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of {@code Thread} should override this method.
     *
     * @see #start()
     * @see #stop()
     */

    @Override
    public void run() {
        super.run();
        System.out.println("Printing zoo inventory");
    }
}
