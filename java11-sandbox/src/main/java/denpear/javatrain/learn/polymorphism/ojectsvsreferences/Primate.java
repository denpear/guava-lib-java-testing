package denpear.javatrain.learn.polymorphism.ojectsvsreferences;

public class Primate {
    public boolean hasHair() {
        return true;
    }

    public static void main(String[] args) {
        Lemur lemur = new Lemur();
        System.out.println(lemur.age);
        HasTail hasTail = lemur;
        System.out.println(hasTail.isTailStriped());
        Primate primate = lemur;
        System.out.println(primate.hasHair());
    }
}
