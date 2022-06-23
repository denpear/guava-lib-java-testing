package denpear.javatrain.learn.constructor.one;

public class Animal {
    private int age;

    public Animal(int age) {
        super(); //Animal, первый оператор конструктора является вызовом родительского
        // конструктора, определенного в java.lang.Object
        this.age = age; //
    }
}


