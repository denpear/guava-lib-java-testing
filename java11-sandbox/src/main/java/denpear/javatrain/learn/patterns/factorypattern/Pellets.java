package denpear.javatrain.learn.patterns.factorypattern;

public class Pellets extends Food {
    public Pellets (int quantity){
        super(quantity);
    }

    @Override
    public void consumed() {
        System.out.println("Pellets eaten: "+ super.getQuantity());
    }
}
