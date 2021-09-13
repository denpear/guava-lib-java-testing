package datastructure;

import org.junit.Test;

import java.util.*;

public class JCFCommonMethods {
    @Test
    public void testAddCommonMethod () {
        List<String> list = new ArrayList<>();
        System.out.println(list.add("Sparrow")); // true
        System.out.println(list.add("Sparrow")); // true

        Set<String> set = new HashSet<>();
        System.out.println(set.add("Sparrow")); // true
        System.out.println(set.add("Sparrow")); // false

        Queue<String> queue = new ArrayDeque<>();
        System.out.println(queue.add("Sparrow")); // true
        System.out.println(queue.add("Sparrow")); // false

    }
}
