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

class HashMapDemo
{

    static void check(String s)
    {
        HashMap<Character,Integer> map=new HashMap<Character,Integer>();
        for(int i=0;i<s.length();i++)
        {char c=s.charAt(i);
            if(!map.containsKey(c))
                map.put(c,1);
            else
                map.put(c,map.get(c)+1);
        }



        Iterator<Character> itr = map.keySet().iterator();
        while (itr.hasNext()) {
            Object x=itr.next();
            System.out.println("count of "+x+" : "+map.get(x));
        }
    }

    public static void main(String[] args)
    {
        String s="hello";
        check(s);
    }
}
