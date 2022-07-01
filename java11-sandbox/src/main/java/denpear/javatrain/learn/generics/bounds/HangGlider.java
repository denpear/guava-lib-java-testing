package denpear.javatrain.learn.generics.bounds;

import java.util.List;

interface Flyer {
    void fly();
}

public class HangGlider implements Flyer {
    @Override
    public void fly() {
        System.out.println("Я летаю на дальтоплане");
    }

    private void anyFlyer(List<Flyer> flyers) {
    }

    ;

    /**
     * Обратите внимание, что мы использовали ключевое слово extends, а не implements.
     * Верхние границы похожи на анонимные классы в том, что они используют extends независимо от того,
     * работаем ли мы с классом или интерфейсом.
     *
     * @param groupOfFlyers
     */
    private void groupOfFlyers(List<? extends Flyer> groupOfFlyers) {
    }

    ;

}

class Goose implements Flyer {
    @Override
    public void fly() {
        System.out.println("А я просто гусь, умею летать");
    }
}
