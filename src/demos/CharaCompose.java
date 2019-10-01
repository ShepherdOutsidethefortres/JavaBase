package demos;

import java.util.Arrays;

/**
 * 输入一个字符串，输出该字符串中字符的所有组合，组合中不能出现重复的字符。
 * 如：
 * abc ==> a、b、c、ab、ac、ba、bc、ca、cb、abc、acb、bac、bca、cab、cba
 * Created by huiwei.zhao on 2019/7/16.
 */
public class CharaCompose {

    public static String str = "abc";

    public static void main(String[] args) {
        show(0,new String());
    }

    /**
     * O(n^3)太复杂了---可以考虑递归
     *
     * @param str
     */
    public static void printCharCompose(String str) {
        int count = 0;
        char[] chars = str.toCharArray();
        char[] chars_copy = Arrays.copyOf(chars, chars.length + 1);
        chars_copy[chars_copy.length - 1] = ' ';

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars_copy.length; j++) {
                for (int k = 0; k < chars_copy.length; k++) {
                    if (chars_copy[j] == ' ' && chars_copy[k] == ' ') {
                        count++;
                        System.out.println(String.valueOf(chars[i]) + String.valueOf(chars_copy[j]) + String.valueOf(chars_copy[k]));
                    } else if (chars[i] != ' ' && chars_copy[j] == ' ' && chars_copy[k] != ' ') {
                        continue;
                    } else if (chars[i] != chars_copy[j] && chars[i] != chars_copy[k] && chars_copy[j] != chars_copy[k]) {
                        count++;
                        System.out.println(String.valueOf(chars[i]) + String.valueOf(chars_copy[j]) + String.valueOf(chars_copy[k]));
                    } else {
                        continue;
                    }
                }
            }
        }

        System.out.println("总共" + count + "种");
    }

    /**
     * 递归法`
     * @param current_recur
     * @param temp
     */
    public static void show(int current_recur, String temp)
    {
        if(current_recur < str.length())
        {
            for(int i = 0; i < str.length(); i++)
            {
                if(!(temp.contains(str.substring(i, i + 1)) ) )
                {
                    String newStr = temp + str.substring(i, i + 1);
                    System.out.println(newStr);
                    show(current_recur + 1, new String(newStr));
                }
            }
        }
    }
}
