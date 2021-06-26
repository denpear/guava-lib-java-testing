import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuavaCollectionsTest {

public static void print (Object ...args){
    Arrays.stream(args).forEach(System.out::println);
}

@Test
public void testListsOfGuava() {
    ArrayList<String> list = new ArrayList<>();
    list.add("first");
    list.add("second");
    list.add("three");

    List<String> list2 = new ArrayList<>() {{
        add("first");
        add("secong");
        add("third");
    }};

    ImmutableList.Builder<Object> objectBuilder = ImmutableList.builder();

    for (int i = 0; i < 100; i++) {
        objectBuilder.add(i);
    }
    Iterable<Object> objectImmutableList = objectBuilder.build();

    List<String> list3 = ImmutableList.of("on", "two", "three");

    list.addAll(list2);
    list.addAll(list3);

    ImmutableMap<String, Integer> map = ImmutableMap.of("srt",1,"str2",2,"str3",3);

    print(list);
    print(objectImmutableList);
    print(map);
}



}
