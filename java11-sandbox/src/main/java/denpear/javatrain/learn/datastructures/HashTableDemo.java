package denpear.javatrain.learn.datastructures;

import java.util.*;

/**
 * https://www.mygreatlearning.com/blog/data-structures-using-java/
 *
 * Хеширование:
 * 1) Использует специальную хэш-функцию
 * 2) Хэш-функция сопоставляет элемент с адресом для хранения.
 * 3) Это обеспечивает доступ в постоянное время
 * 4) Коллизии обрабатываются с помощью техники разрешения коллизий
 * 5) Техника разрешения коллизий
 *      5.1 Цепочка
 *      5.2 Открытая адресация
 * Преимущества:
 * 1) Хэш-функция помогает получить элемент за постоянное время
 * 2) Эффективный способ хранения элементов
 * Недостатки:
 * 1) Разрешение коллизий увеличивает сложность
 * Приложения:
 * + Подходит для приложений, которым требуется выборка за постоянное время
 *
 */

class HashTableDemo {
    public static void main(String[] arg)
    {
        // creating a hash table
        Hashtable<Integer, String> h =
                new Hashtable<Integer, String>();

        Hashtable<Integer, String> h1 =
                new Hashtable<Integer, String>();

        h.put(3, "Geeks");
        h.put(2, "forGeeks");
        h.put(1, "isBest");

        // create a clone or shallow copy of hash table h
        h1 = (Hashtable<Integer, String>)h.clone();

        // checking clone h1
        System.out.println("values in clone: " + h1);

        // clear hash table h
        h.clear();

        // checking hash table h
        System.out.println("after clearing: " + h);
        System.out.println("values in clone: " + h1);


    }
}