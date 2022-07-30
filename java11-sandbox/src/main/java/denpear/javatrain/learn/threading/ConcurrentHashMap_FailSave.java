package denpear.javatrain.learn.threading;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMap_FailSave {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.put("First", 10);
        map.put("Second", 20);
        map.put("Third", 30);
        map.put("Forth", 40);

        Iterator<String> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key);
            map.put("Fifth", 50);
        }
    }
}
