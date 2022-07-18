package denpear.javatrain.learn.threading;

class RunnableThread {
    public void runnableThread() {
        Thread t = new Thread(() -> {});
        t.start () ;
// RUNNABLE
        System.out.println("RunnableThread : " + t.getState());
    }

    public static void main (String[] args){
        RunnableThread rt = new RunnableThread();
        rt.runnableThread();
    }

}
