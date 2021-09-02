package denpear.javatrain.learn.patterns.immutableobjects.mutable;

import java.util.List;

/**
 * Благодаря этому изменению вызывающая сторона, создающая объект, использует ту же ссылку,
 * что и неизменяемый объект, что означает, что у него есть возможность изменить Список!
 * Важно при создании неизменяемых объектов важно, чтобы любые изменяемые входные аргументы копировались в объект вместо того,
 * чтобы использоваться напрямую.
 */

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
    // ВНИМАНИЕ: передается "живая" ссылка на объект!
    public List<String> getList() {
        return this.list = list;
    }
}

