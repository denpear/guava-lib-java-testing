package denpear.javatrain.learn.threading;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;

public class HashMap_FailFast {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("First", 10);
        map.put("Second", 20);
        map.put("Third", 30);
        map.put("Forth", 40);

        try {
            Iterator<String> iterator = map.keySet().iterator();

            while (iterator.hasNext()) { // while (iterator.hasNext()) печатает последний ключ в коллекции! Потом сразу ичключение
                String key = iterator.next();
                System.out.println("Печатаем последний ключ в коллекции! Потом сразу исключение: ");
                System.out.println(key);
                map.put("Fifth", 50);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("А вот и наше ConcurrentModificationException исключение! ");
        } finally {
            System.out.println("Блок finally все равно был выполнен! ");
        }
    }
}
