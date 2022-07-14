package denpear.javatrain.learn.algorithms.indexing;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TopUniqueWordCounter_2 {
    static Map<String, Long> map = new TreeMap<>();

    /**
     * Слово это последовательность непробельных символов. Нужно посчитать кол-во уникальных слов без учета регистра в
     * тексте представленным коллекций <code>lines</code>. Вернуть <code>topN</code> cамых часто используемых
     * <p>
     * Порядок построения решения при livecording: источник (структура) - данных в точке останова - план решения - решение
     * 1) задуматься как получить в ожидаемый интервьюьером метод искомую структутру данных, а уже потом набор тестовых даннных
     * 2) Зафиксировать точку останова с тестовыми данными в проектирокуемом методе, БЕЗ ПАРАМЕТРИЗОВАННЫХ ТЕСТОВ!
     * 3) Четкий план решения готовим на бумаге
     * 4) С этого момента можно оценить степень сложности разных подходов к решению
     * 5) Можно начинать гуглить узловые шаги схемы решения проблемы
     */

    public static Map<String, Long> count(Collection<String> lines, int topN) {
        // сначала разбиваем текст на строки, строки на слова: Функция mapper, переданная в flatMap,
        // разбивает строку с помощью простого регулярного выражения на массив слов, а затем создает поток слов из этого массива.
        map = lines.stream().flatMap(line -> Stream.of(line.split("\\s+"))).collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        //Способ №2 Сортировка Map в Stream ссылка на метод c выводом двух часто встречающихся
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey).collect(Collectors.toList()).subList(0, topN).stream().forEach(System.out::println);
        return map;
    }

    public static void main(String[] args) {
        Collection<String> stringCollection = Stream.of("четыре два три три четыре ", "четыре один два три четыре ").collect(Collectors.toCollection(ArrayList::new));
        map = count(stringCollection, 3);
        System.out.println(map);
    }

}