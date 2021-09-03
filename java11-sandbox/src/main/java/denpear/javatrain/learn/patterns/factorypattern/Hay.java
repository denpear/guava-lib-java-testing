package denpear.javatrain.learn.patterns.factorypattern;

public class Hay extends Food {
    public Hay(int quantity) {
        super(quantity);
    }
    public void consumed() {
        System.out.println("Hay eaten: "+ super.getQuantity());
    }
}
