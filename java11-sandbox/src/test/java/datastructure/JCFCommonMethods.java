package datastructure;

import org.junit.Test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    @Test
    public void testSerializable(){
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        String id = now.toString();
   //     Serializable id = disassemble(now);;
        System.out.println("Seq is " + id);
    }

    /**
     * Возвращает сериализованное представление.
     * Визуально разницы никакой.
     * @param value
     * @return
     */
    public Serializable disassemble(Object value) {
        return value.toString();
    }

}
