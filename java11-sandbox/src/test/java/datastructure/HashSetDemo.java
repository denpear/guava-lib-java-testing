package datastructure;

import org.junit.Test;

import java.util.HashSet;

public class HashSetDemo {
    @Test
    public void checkCharactersInHashSet(){
        String s="helo wqty and фсем Привэт! ";
        boolean ans=isUnique(s);
        if(ans)
            System.out.println("string has unique characters");
        else
            System.out.println("string does not have unique characters");

    }

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

}
