package denpear.javatrain.learn.generics.bounds;

import java.util.ArrayList;
import java.util.List;

public class Sparrow extends Bird {

    public static void main(String[] args) {
        List<? extends Bird> birds = new ArrayList<Bird>();
        //birds.add(new Sparrow()); // DOES NOT COMPILE
        //birds.add(new Bird()); // DOES NOT COMPILE
    }
};

class Bird {
};


