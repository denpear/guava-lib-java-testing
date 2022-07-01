package denpear.javatrain.learn.collections;

import java.util.NavigableSet;
import java.util.TreeSet;

public class NavigableSetDemo_134 {
    public static void main(String[] args) {
        NavigableSet<Integer> set = new TreeSet<>();
        for (int i = 0; i <= 20; i++) set.add(i);
        System.out.println(set.lower(10)); //наибольший, что меньше e
        System.out.println(set.floor(10)); //наибольший, что меньше e, либо равно e
        System.out.println(set.ceiling(2)); //наименьший, что больше, либо равно e
        System.out.println(set.higher(2)); //наименьший, что больше e
    }
}
