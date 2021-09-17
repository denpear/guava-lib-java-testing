package denpear.javatrain.learn.objectmapper;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.Test;

import java.io.IOException;

import static guavagooglelibrary.GuavaCollectionsTest.print;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class MyDtoTest {
    @Test
    public void givenFieldIsIgnoredByName_whenDtoIsSerialized_thenCorrect()
            throws JsonParseException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        MyDto dtoObject = new MyDto();
        String dtoAsString = mapper.writeValueAsString(dtoObject);
        print(dtoAsString);
        assertThat(dtoAsString, not(containsString("intValue")));
    }

    @Test
    public final void givenTypeHasFilterThatIgnoresFieldByName_whenDtoIsSerialized_thenCorrect()
            throws JsonParseException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("intValue", "booleanValue");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("myFilter", theFilter);

        MyDtoWithFilter dtoObject = new MyDtoWithFilter();
        String dtoAsString = mapper.writer(filters).writeValueAsString(dtoObject);

        assertThat(dtoAsString, not(containsString("intValue")));
        assertThat(dtoAsString, not(containsString("booleanValue")));
        assertThat(dtoAsString, containsString("stringValue"));
        System.out.println(dtoAsString);
    }

    @JsonFilter("myFilter")
    public class MyDtoWithFilter {
        private String stringValue;
        private int intValue;

        public String getStringValue() {
            return stringValue;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }

        public void setIntValue(int intValue) {
            this.intValue = intValue;
        }

        public boolean isBooleanValue() {
            return booleanValue;
        }

        public void setBooleanValue(boolean booleanValue) {
            this.booleanValue = booleanValue;
        }

        private boolean booleanValue;
        public MyDtoWithFilter() {super();}

         }

}