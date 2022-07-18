package denpear.javatrain.learn.threading.ocp_ontopic7_examples;

import java.util.HashMap;
import java.util.Map;

public class ZooManager_358_36 {
    private Map<String,Object> foodData = new HashMap<>();
    public synchronized void put (String key, String value){
        foodData.put(key,value);
    }
    public synchronized Object get(String key){
        return foodData.get(key);
    }
}
