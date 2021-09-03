package denpear.javatrain.learn.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Duck {
    private String name;
    private int weight;
    public Duck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Duck{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    public static void main(String[] args) {
        Comparator<Duck> byWeight = new Comparator<Duck>(){
            public int compare (Duck d1, Duck d2){
                return d1.getWeight() - d2.getWeight();
            }
        };
// to energise the comparator
        // Method number 1 via new
        Comparator <Duck> byName = new Comparator<Duck>() {
            public int compare(Duck o1, Duck o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        // Method number 2 via lambda
        Comparator <Duck> byName2 = (o1, o2) -> o1.getName().compareTo(o2.getName());
        // Method number 3 via Comparator.comparing
        Comparator <Duck> byName3 = Comparator.comparing(Duck::getName);





        List <Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("Seriy",7));
        ducks.add(new Duck("Beliy",10));
        Collections.sort(ducks,byWeight); // sort by weight

        System.out.println("After combat use of the byWeight comparator ");
        System.out.println(ducks);
        System.out.println("After combat use of the byName comparator ");
        Collections.sort(ducks,byName2); // sort by name
        System.out.println(ducks);
    }

}
