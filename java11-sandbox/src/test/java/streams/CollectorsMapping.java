package streams;

import org.junit.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class CollectorsMapping {

    /**
     * вы можете избавиться от Optional<Character>, если хотите, используя 'collectAndThen'.
     */

    @Test
    public void usingMappingCollectorCollectingAndThen() {
        Stream<String> stream = Stream.of("lions", "tigers", "bears");
        Comparator<Character> charComparator = Comparator.naturalOrder();
        Map<Integer, Character> map = stream
                .collect(
                        groupingBy(
                                String::length,
                                mapping(
                                        s -> s.charAt(0),
                                        collectingAndThen(
                                                minBy(charComparator),
                                                Optional::get
                                        )
                                )
                        )
                );
        System.out.println(map); // {5=b, 6=t}
    }

    /**
     * Просто чтобы было ясно, это не (обязательно) ошибка Intellij.
     * В отчете об ошибке говорится, что Intellij предполагает, что свидетель типа устарел, тогда как компиляция без свидетеля типа завершается неудачно (используя javac за кулисами). Таким образом, Intellij и javac не согласны с тем, необходим ли свидетель типа.
     * Обратите внимание, что Eclipse компилирует код без свидетеля типа. Я думаю, на этот раз ошибка на стороне javac.
     * <p>
     * java: no suitable method found for groupingBy(String::length,java.util.stream.Collector<java.lang.Object,capture#1 of ?,java.util.Optional<T>>)
     * method java.util.stream.Collectors.<T,K,D,A,M>groupingBy(java.util.function.Function<? super T,? extends K>,java.util.function.Supplier<M>,java.util.stream.Collector<? super T,A,D>) is not applicable
     * (cannot infer type-variable(s) T,K,D,A,M
     * (actual and formal argument lists differ in length))
     * method java.util.stream.Collectors.<T,K,A,D>groupingBy(java.util.function.Function<? super T,? extends K>,java.util.stream.Collector<? super T,A,D>) is not applicable
     * (inference variable U has incompatible upper bounds java.lang.Object,java.lang.Comparable<? super T>,T,T)
     * method java.util.stream.Collectors.<T,K>groupingBy(java.util.function.Function<? super T,? extends K>) is not applicable
     * (cannot infer type-variable(s) T,K
     * (actual and formal argument lists differ in length))
     */

    @Test
    public void usingMappingCollectorCharacterWitness() {
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> map = ohMy.collect(
                groupingBy(
                        String::length,
                        mapping(s -> s.charAt(0),
                                minBy(Comparator.<Character>naturalOrder())
                        )
                ));
        System.out.println(map); // {5=Optional[b], 6=Optional[t]}
    }
}



