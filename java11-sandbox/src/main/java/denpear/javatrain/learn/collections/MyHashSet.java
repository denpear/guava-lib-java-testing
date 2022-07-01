package denpear.javatrain.learn.collections;

import java.util.HashSet;
import java.util.Set;

public class MyHashSet {

    public static void main(String[] args) {
        Set<Integer> integerSet = new HashSet<>();
        boolean b1 = integerSet.add(66);
        boolean b2 = integerSet.add(16);
        boolean b3 = integerSet.add(66);
        boolean b4 = integerSet.add(8);

        for (Integer integer : integerSet) System.out.println(integer + ",");
        integerSet.stream().map(integer -> integer + ";").forEach(System.out::println);
    }

}
