package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ZooManager_359_37 {
    private Map<String, Object> foodData = new ConcurrentHashMap<>();

    public void put(String key, String value) {
        foodData.put(key, value);
    }

    public Object get(String key) {
        return foodData.get(key);
    }
}
