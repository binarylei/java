package com.github.binarylei.javase.string;

import org.junit.Test;

/**
 * String 不可变字符
 */
public class StringTest {
    
    @Test
    public void test() {
        String str = new String("abcd");
        String str2 = new String("abcd");
        System.out.println(str2.equals(str));
        System.out.println(str2 == str);

        System.out.println(str.charAt(2));

        String str3 = "def";
        String str4 = "def";
        System.out.println(str3.equals(str4));
        System.out.println(str3 == str4);

        System.out.println(str3.indexOf('y'));
        String s = str3.substring(0);
        System.out.println(s);
        String str5 = str3.replace('e', '*');
        System.out.println(str5);

        String str6 = "abcde,rrtt,cccee";
        String[] strArray = str6.split(",");
        for(int i=0;i<strArray.length;i++){
            System.out.println(strArray[i]);
        }
        String str7 = "  aa  bb  ";
        String str77 = str7.trim();
        System.out.println(str77.length());

        System.out.println("Abc".equalsIgnoreCase("abc"));
        System.out.println("Abcbd".indexOf('b'));
        System.out.println("Abcbd".lastIndexOf('b'));
        System.out.println("Abcbd".startsWith("Ab"));
        System.out.println("Abcbd".endsWith("bd"));
        System.out.println("Abcbd".toLowerCase());
        System.out.println("Abcbd".toUpperCase());

        System.out.println("##################");
        String gh = new String("a");
        for (int i = 0; i < 1000; i++) {
            gh = gh + i;
        }
        System.out.println(gh);
    } 
}
