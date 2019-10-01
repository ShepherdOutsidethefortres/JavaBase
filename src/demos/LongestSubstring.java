package demos;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例
 * 输入: "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

 输入: "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

 输入: "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 * Created by huiweizhao on 2019/8/26.
 */
public class LongestSubstring {

    public static void main(String[] args) {
        String str = "bbbbb";

        System.out.printf(lengthOfLongestSubstring(str));
    }

    public static String lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        //用于记录最大值的sb2
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            String s1 = sb1.toString();

            if (s1.contains(Character.toString(chars[i]))) {
                if (sb1.length() > sb2.length()) {
                    sb2.delete(0, sb2.length());
                    sb2.append(sb1);
                    sb1.delete(0, sb1.length());
                    sb1.append(chars[i]);
                }
            } else {
                sb1.append(chars[i]);
            }
        }

        return sb2.toString();
    }
}
