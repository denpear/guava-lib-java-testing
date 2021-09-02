package denpear.javatrain.learn.patterns.immutableobjects.immutable;

import java.util.ArrayList;
import java.util.List;

public class MyImmutable {

    private String str;
    private List<String> list;

    public MyImmutable(String str, List<String> list) {
        this.str = str;
        this.list = list;
    }

    public String getStr() {
        return str;
    }

    public List<String> getList() {
        return this.list = new ArrayList();
    }

    public String getFavoriteListPoint(int index) {
        return list.get(index);
    }
}

