package streams;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapTests {
    static Map<String, Long> map = new TreeMap<>();

    public static Map<String, Long> count(Collection<String> lines, int topN) {
        // Фокус 1): сначала разбиваем текст на строки, строки на слова: Функция mapper, переданная в flatMap,
        // разбивает строку с помощью простого регулярного выражения на массив слов, а затем создает поток слов из этого массива.
        // Фокус 2): что можно считать повторения при помощи комбинации Collectors.counting() внутри Collectors.groupingBy
        // Фокус 3): сортировка Map в обратном порядке по значению Long в Словаре
        // Фокус 4): как резать Map при помощи limit()
        map = lines.stream().flatMap(line -> Stream.of(line.split("\\s+"))).collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(topN).forEach(System.out::println);
        return map;
    }

    @Test
    public void getMyTestFromFileIndexing() throws IOException {
        Path path = Paths.get("C:\\dev\\CODE\\TpamCrd\\lib-java-testing\\java11-sandbox\\src\\test\\resources\\testText.txt");
        // read file into a stream of lines
        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        // result a stream of words, good!
        Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));
        Collection<String> stringCollection = words.collect(Collectors.toCollection(ArrayList::new));
        map = count(stringCollection, 3);
    }

    @Test
    public void getMyStringsIndexing() {
        Collection<String> stringCollection = Stream.of("четыре два три три четыре ", "четыре один два три четыре ").collect(Collectors.toCollection(ArrayList::new));
        map = count(stringCollection, 3);
    }

    @Test
    public void makeOnFlatFromTwoStructures() {
        String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        String[] result = Stream.of(array).flatMap(Stream::of).toArray(String[]::new);
        for (String s : result) {
            System.out.println(s);
        }
    }

    @Test
    public void makeOnFlatFromTwoStructuresFilter() {
        String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        List<String> stringList = Stream.of(array).
                flatMap(Stream::of)
                .filter(x -> !"f".equals(x))
                .collect(Collectors.toList());

        stringList.forEach(System.out::println);

    }

    @Test
    public void getSplattedTextFromFile() throws IOException {
        Path path = Paths.get("C:\\dev\\CODE\\TpamCrd\\lib-java-testing\\java11-sandbox\\src\\test\\resources\\testText.txt");
        // read file into a stream of lines
        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        // result a stream of words, good!
        Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));
        // count the number of words.
        long noOfWords = words.count();
        System.out.println(noOfWords);  // 16
    }


    @Test
    public void getStringStreamFromGenerate() {
        Stream.generate(() -> "Elsa")
                .filter(n -> n.length() == 4)
                .limit(2)
                .sorted()
                .forEach(System.out::println);
    }

}
