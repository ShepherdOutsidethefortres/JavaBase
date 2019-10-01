package demos;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给一个字符串，字符串里有（）{}[]“”这六个符号，设计一个算法,判断这些符号是否成对匹配，即要检验这些括号是否都是成对出现的
 * 先将这些符号遍历择出来，然后一个个压入栈中
 * 1、将字符串的每个字符进行遍历
 * 2、如果发现是左括号，那么将该字符压入到栈中
 * 3、如果是右括号，先去存储好的栈顶找到相应的值
 * 4、若栈为空返回false，若匹配，pop该左括号，若不匹配也返回false
 * 5、最后看存储栈中的做括号是否都匹配上了，也就是栈最后为空，返回true，否则返回false
 * <p>
 * <p>
 * 是否回文（关于中心左右对称）：
 * （1）将str和str reverse后的字符串，遍历一一对比
 * （2）
 * Created by huiwei.zhao on 2019/6/24.
 */
public class CharPairMatching {
    public static void main(String[] args) {
        String str = "abac(ssss(ssss)ssbbfjskl{sjlfs}sjsjsl}sss{sssfs[sss[sss]sss[[]sss[";
        String str2 = "[]{}()";

        System.out.println("是否成对：" + isPairMatching(str2));

    }

    public static boolean isPairMatching(String test) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < test.length(); i++) {
            char c = test.charAt(i);
            if (c == '{' || c == '}' || c == '(' || c == ')' || c == '[' || c == ']') {
                strBuilder.append(c);
            }
        }

        String str = strBuilder.toString();

        Map<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');

        Stack stack = new Stack();

        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            //是否为左括号
            if (map.containsKey(c)) {
                stack.push(c);
                //是否为右括号
            } else if (map.containsValue(c)) {
                if (stack.isEmpty()) {
                    return false;
                } else if (map.get(stack.peek()).equals(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();

    }

    public static boolean isHuiWen(String str) {
        StringBuilder strBui = new StringBuilder(str);
        StringBuilder revStr = strBui.reverse();

        return str.equals(revStr.toString());
    }

    public static boolean isHuiWen2(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
