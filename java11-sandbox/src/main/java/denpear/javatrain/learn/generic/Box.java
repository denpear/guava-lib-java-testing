 package denpear.javatrain.learn.generic;

/*Определяем обобщенный класс*/
public class Box<T> {
    private T t;

    public void add (T t){
        this.t = t;
    }

    public T get(){
        return t;
    }


    public static void main (String[] args){
        Box integerBox = new Box();
        Box stringBox = new Box();

        integerBox.add(new Integer(10));
        stringBox.add(new String("Hello, World"));

        System.out.printf("Значение Integer :%d\n\n", integerBox.get());
        System.out.printf("Значение String :%s\n", stringBox.get());

    }

}
