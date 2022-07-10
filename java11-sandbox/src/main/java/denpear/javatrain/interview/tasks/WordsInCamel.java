package denpear.javatrain.interview.tasks;

public class WordsInCamel {
    /**
     * Строка str представляет собой последовательность слов в CamelCase,
     * она состоит из одного или более слов и только из латинских букв.
     * Все буквы первого слова в нижнем регистре, для каждого последующего слова первая буква в верхнем регистре,
     * а остальные буквы в нижнем
     * Задача: реализуйте функцию которая возвращает количество слов в строке str
     * public int wordsCount(String str)
     * пример str="veryThinDog" ответ 3
     *
     * @param str
     * @return
     */

    public static int wordsCount(String str) {

        String text = str.replaceAll("(?=[A-Z]+)", " ").trim();
        String[] words = text.split("\\s+");
        System.out.println(words.length);
        return words.length;

    }

    public static void main(String[] args) {
        String str = "veryThinDogRod";
        wordsCount(str);
    }

}
