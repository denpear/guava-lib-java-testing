package denpear.javatrain.learn.threading;

public class ProcessWait {


    public static void main(String[] args) throws InterruptedException {
        String paramString = "Net6";
        new MyHouse().process(paramString);
    }
}

/**
 * https://stackoverflow.com/questions/30262209/exception-in-thread-main-java-lang-illegalmonitorstateexception �����
 * ���������� � IllegalMonitorStateException, ���������� ���������, ��� ��� ������ ������ wait ���������� ������ �����,
 * ����� ���������� ����� ������� ��������������� ���������. ����� ������� ������� - ��������� ��� ������ ������
 * ������������������ ������. ������ �������������, ������� ������ ���� ������ � ��������� synchronized, �������� ���,
 * ��� ������� ������ ���� �������.
 */
class MyHouse {
    private boolean pizzaArrived = false;

    public void process(String param) throws InterruptedException {
        synchronized (this) {
            System.out.println("Awaiting signal...");

            wait(3000);

            System.out.println("param = " + param);
        }
    }

    public void eatPizza() throws InterruptedException {
        synchronized (this) {
            while (!pizzaArrived) {
                wait();
            }
        }
        System.out.println("yumyum..");
    }

    public void pizzaGuy() {
        synchronized (this) {
            this.pizzaArrived = true;
            notifyAll();
        }
    }
}