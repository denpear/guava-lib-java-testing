package strings;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubString {

    @Test
    public void extractSubjectDNSubString() {
        String patternStr = "[^CN=].*?[^,]*";///"(CN=).[^,]*"; ///(CN=).[^,]*/g
        Pattern pattern = Pattern.compile(patternStr);
        var vDN = "CN=svedev.common.name.com, OU=Security Engineering, O=Lightweight, C=COM";
        Matcher matcher = pattern.matcher(vDN);
        if (matcher.find()) System.out.println(matcher.group(0));

    }

}
