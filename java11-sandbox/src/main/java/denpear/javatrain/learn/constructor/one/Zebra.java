package denpear.javatrain.learn.constructor.one;

public class Zebra extends Animal {
    public Zebra(int age) {
        super(age); // первый оператор первого конструктора является вызовом конструктора Animal,
        // который принимает один аргумент
    }

    public Zebra() {
        this(4); // из констурктора по умолчанию вызван констурктор этого же класса с одним параметром
    }

    public static void main(String... args) {
        Zebra zebra = new Zebra();
        System.out.println("Зебра родилась! " + zebra.toString());
    }
}
