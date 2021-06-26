import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OCAJavaSE8Test {
    public static void print (Object ...args){
        Arrays.stream(args).forEach(System.out::println);
    }

    @Test
    public void LineNumbers () {
        List<String> stringArrayList = new ArrayList<String>();
        if (stringArrayList.isEmpty()) {
            print("Пустой");
            print(56); // 56
            print(0b11); // 3
            print(017); // 15
            print(0x1F); // 31
        }
        else print("НЕ Пустой");
        }
}
