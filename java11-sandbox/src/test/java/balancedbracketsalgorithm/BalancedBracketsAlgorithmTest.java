package balancedbracketsalgorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BalancedBracketsAlgorithmTest {

    public static void print(Object... args) {
        Arrays.stream(args).forEach(System.out::println);
    }


    /**
     * https://www.baeldung.com/java-balanced-brackets-algorithm
     */

    @Test
    public void  basedOnBasicSetupAndValidations() {

        String str = "(литерал в скобках)";

        if (null == str || ((str.length() % 2) == 0)) {
            print("false");
        } else {
            char[] ch = str.toCharArray();
            for (char c : ch) {
                if (!(c == '{' || c == '[' || c == '(' || c == '}' || c == ']' || c == ')')) {
                    print("false2");
                }
            }
        }
    }

    @Test
    public void isStringHasSubstring(){
        String s = "(литерал в скобках)";
        Pattern p = Pattern.compile("(\\(.*\\))");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        String is = b ? "Верно!" : "Не верно!";
        print(is);

        if (b) {print("Содержит открывающию круглую скобку");
            if (s.contains(")")) { {print("Содержит закрывающию круглую скобку");}
            }
        }
    }

}
