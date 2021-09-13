package datastructure;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTests {
    /**
     * Первый вызов merge() вызывает функцию отображения (remapping function)
     * и складывает два числа, чтобы получить 13.  Затем он обновляет карту.
     * Второй вызов merge() видит, что в настоящее время в карте есть нулевое
     * значение для этого ключа.
     * Он не вызывает функцию отображения, а заменяет его новым значением 3.
     */

    @Test
    public void HashMapMerge (){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, null);
        map.merge(1, 3, (a,b) -> a + b);
        map.merge(3, 3, (a,b) -> a + b);
        System.out.println(map); //{1=13, 2=20, 3=3}
    }
}
