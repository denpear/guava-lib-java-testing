package denpear.javatrain.learn.algorithms.indexing;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TopUniqueWordCounter {
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
        for (String line : lines) {
            String[] array = line.replaceAll("\\pP", " ").
                    toLowerCase().
                    split("\\s+");

            for (int i = 0; i < array.length; i++) {
                String term = array[i];
                incrementTermCount(term);
            }
        }
        List<Long> populer = map.entrySet().stream().map(stringLongEntry -> stringLongEntry.getValue()).sorted().collect(Collectors.toList())
                .subList(map.size() - 2, map.size()); // тут слепые Long

        //Способ №1 Сортировка Map в Stream
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(stringLongEntry -> stringLongEntry.getKey()).collect(Collectors.toList()).subList(0, 2);

        //Способ №2 Сортировка Map в Stream ссылка на метод c выводом двух часто встречающихся
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey).collect(Collectors.toList()).subList(0, 2).stream().forEach(System.out::println);

        //Способ №3 Сортировка Map через loop
        List<Map.Entry<String, Long>> toSort = new ArrayList<>();
        for (Map.Entry<String, Long> stringLongEntry : map.entrySet()) {
            toSort.add(stringLongEntry);
        }
        toSort.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Long> stringLongEntry : toSort) {
            String key = stringLongEntry.getKey();
            list.add(key);
        }
        list.subList(0, 2);

        return map;
    }

    public static void main(String[] args) {
        Collection<String> stringCollection = Stream.of("четыре два три три четыре ", "четыре один два три четыре ").collect(Collectors.toCollection(ArrayList::new));
        map = count(stringCollection, 10);
        System.out.println(stringCollection);
    }

    public static void incrementTermCount(String term) {
        // System.out.println(term);
        put(term, get(term) + 1);
    }

    public static void put(String term, long count) {
        map.put(term, count);
    }

    public static Long get(String term) {
        Long count = map.get(term);
        return count == null ? 0 : count;
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public int size() {
        // TODO: метод
        return 0;
    }

    public void printCounts() {
        for (String key : keySet()) {
            Long count = get(key);
            System.out.println(key + ", " + count);
        }
        System.out.println("Total of all counts = " + size());
    }
}