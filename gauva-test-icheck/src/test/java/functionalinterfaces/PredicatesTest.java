package functionalinterfaces;

import org.junit.Test;

import java.util.*;

public class PredicatesTest {
    /**
     * Java 8 introduces a new method called removeIf. Before this, we had the ability to remove
     * a specified object from a collection or a specified index from a list. Now we can specify
     * what should be deleted using a block of code.
     * The method signature looks like this:
     * boolean removeIf(Predicate<? super E> filter)
     */

    @Test
    public void removingConditionally() {
        List<String> list = new ArrayList<>();
        list.add("Magician");
        list.add("Assistant");
        System.out.println(list); // [Magician, Assistant]
        list.removeIf(s -> s.startsWith("A"));
        System.out.println(list); // [Magician]
    }

    /**
     * Another new method introduced on Lists is replaceAll. Java 8 lets you pass a lambda
     * expression and have it applied to each element in the list. The result replaces the current
     * value of that element.
     * The method signature looks like:
     * void replaceAll(UnaryOperator<E> o)
     */

    @Test
    public void updatingAllElements(){
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.replaceAll(x -> x*2);
        System.out.println(list); // [2, 4, 6]
    }

    @Test
    public void putIfAbsent(){
        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", null);
        System.out.println(favorites); // {Tom=Tram, Jenny=Bus Tour, Sam=Tram}
        favorites.putIfAbsent("Jenny", "Tram");
        favorites.putIfAbsent("Sam", "Tram");
        favorites.putIfAbsent("Tom", "Tram");
        System.out.println(favorites); // {Tom=Tram, Jenny=Bus Tour, Sam=Tram}
    }

}
