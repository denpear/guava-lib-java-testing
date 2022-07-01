package denpear.javatrain.learn.collections;

import java.util.Set;
import java.util.TreeSet;

public class MyTreeSet {

    public static void main(String[] args) {
        Set<Integer> integerSet = new TreeSet<>();
        boolean b1 = integerSet.add(66);
        boolean b2 = integerSet.add(16);
        boolean b3 = integerSet.add(66);
        boolean b4 = integerSet.add(8);

        for (Integer integer : integerSet) System.out.println(integer + ",");
        integerSet.stream().map(integer -> integer + ";").forEach(System.out::println);
    }

}
