package demos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * aabbcccddddccccc输入一串字符串，输出连续重复出现次数最多的字符和次数
 * 关键点：如何判断连续重复出现
 * Created by huiwei.zhao on 2019/7/17.
 */
public class RepeatSearch {
    public static void main(String[] args) {
        String str = "aabbcccddddccccc";
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            int n = 0;
            int j = i + 1;

            while (j < str.length()) {
                if (str.charAt(i) == str.charAt(j)) {
                    n++;
                } else {
                    break;
                }

                j++;
            }

            map.put(str.charAt(i), n);

        }


        Set<Character> keySet = map.keySet();
        Iterator<Character> iterator = keySet.iterator();
        int max = 0;
        Character character = 'a';


        while (iterator.hasNext()) {
            Character next = iterator.next();
            if (map.get(next) > max) {
                max = map.get(next);
                character = next;
            }
        }

        System.out.println("char:"+character+",max:"+max);
    }
}
