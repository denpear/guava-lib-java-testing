package denpear.javatrain.learn.algorithms.recursion;

import java.util.ArrayList;

public class CharCombination {
    private static char[] charComb;
    private static int cnt;

    static void recursiveGenMyPassWord(int indexCharArray, int passwordSize) {


        if (indexCharArray == passwordSize) {
            cnt++;
            System.out.println(String.valueOf(charComb) + " вызов номер " + cnt);
            return;
        }

        int p = 0;
        ArrayList<Character> possible = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) { //  внесли СТРОЧНЫЕ
            possible.add(p++, c);
        }
        for (char c = 'A'; c <= 'Z'; c++) { //  внесли ЗАГЛАВНЫЕ
            possible.add(p++, c);
        }
        for (char c = '0'; c <= '9'; c++) { //  внесли ЦИФРЫ
            possible.add(p++, c);
        }

        for (Character c : possible) {
            charComb[indexCharArray] = c;// перебираем не цифры, а индексы
            if (indexCharArray > 0) {
                if (charComb[indexCharArray] == charComb[indexCharArray - 1]) continue; //238328 итераций за счет
                // требования уникальности какждого символа в пароле уменьшились до 41492
            }
            recursiveGenMyPassWord(indexCharArray + 1, passwordSize);
        }
    }

    public static void main(String[] args) {
        charComb = new char[3]; // регулируем размер комбинации, длину пароля
        recursiveGenMyPassWord(0, charComb.length);
    }
}
