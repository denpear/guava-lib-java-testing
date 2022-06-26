package denpear.javatrain.learn.threading.ocp_ontopic_examples;

public class CheckResults_1_334 {
    private static int counter = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 700; i++) {
                CheckResults_1_334.counter++;
            }
        }).start();
        while (CheckResults_1_334.counter < 100) {
            System.out.println("Еще не достигнут предел");
        }
        System.out.println("Достигнуто!");
    }
}
