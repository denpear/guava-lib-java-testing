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

class HashSetDemo{

    static boolean isUnique(String s)
    {
        HashSet<Character> set =new HashSet<Character>();

        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(c==' ')
                continue;
            if(set.add(c)==false)
                return false;
        }
        return true;
    }


    public static void main(String[] args)
    {
        String s="helo wqty ";
        boolean ans=isUnique(s);
        if(ans)
            System.out.println("string has unique characters");
        else
            System.out.println("string does not have unique characters");
    }
}
