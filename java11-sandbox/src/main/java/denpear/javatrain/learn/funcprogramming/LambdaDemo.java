package denpear.javatrain.learn.funcprogramming;

// My Consumer
interface MyConsumer<T> {
    void doSmth(T t);
}

// MyFuncInterface
interface MyFuncInterface<T> {
    void doSmth();
}


class LambdaDemo {
    public static void main(String[] args) {
        MyConsumer<String> myConsumer = System.out::println;
        myConsumer.doSmth("Hello mister Artem");
        String walk = "walk";
        new LambdaDemo().lambda(() -> System.out.println("like run() started ... ")); // Анонимный внутренний класс - это локальный внутренний класс, не имеющий имени. Он объявляется и инстанцируется в одном операторе с помощью ключевого слова new.
        new LambdaDemo().lambda(() -> System.out.println(walk));
        new LambdaDemo().lambda(myConsumer, "D");
        MyConsumer<String> myConsumerLocal = new MyConsumer<>() {
            @Override
            public void doSmth(String s) {
                System.out.println(s);
            }
        };
        myConsumerLocal.doSmth("Печатай!");

        /**
         * Анонимный внутренний класс одинаково независим от того, реализуете ли вы интерфейс или расширяете класс!
         * Можно не объявлять, но инстанциировать интерфейс, есть представить реализацию, тут же, т.е. анонимно
         */
        new MyConsumer<String>() {
            @Override
            public void doSmth(String s) {
                System.out.println("Своя реализация: " + s);
            }
        }.doSmth("Denis");

        /**
         * аналог анонимной реализации, выраженный через лямбда
         */
        ((MyConsumer<String>) s -> System.out.println("Лямбда реализация: " + s)).doSmth("Denis");
        ((MyConsumer<String>) System.out::println).doSmth("Denis");
        ((MyConsumer<Integer>) integer -> System.out.println(100 + integer)).doSmth(43);

        ((MyConsumer<String>) consumer -> System.out.println(consumer)).doSmth("Denis");


    }

    public void lambda(MyFuncInterface myFuncInterface) {
        myFuncInterface.doSmth();
    }

    public void lambda(MyConsumer myFuncInterface, String str) {
        myFuncInterface.doSmth(str);
    }
}

