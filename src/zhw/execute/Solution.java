package zhw.execute;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试ZuoConTest 类
 */
public class Solution {
    private static char[] is = new char[] { '1', '2', '4', '5'};
    private static int total;
    private static int m = 3;
    public static void main(String[] args) {
        List<Integer> iL = new ArrayList<Integer>();
        new Solution().plzh("", iL,  m);
        System.out.println("total : " + total);
    }
    private void plzh(String s, List<Integer> iL, int m) {
        if(m == 0) {
            System.out.println(s);
            total++;
            return;
        }
        List<Integer> iL2;
        for(int i = 0; i < is.length; i++) {
            iL2 = new ArrayList<Integer>();
            iL2.addAll(iL);
            if(!iL.contains(i)) {
                String str = s + is[i];
                iL2.add(i);
                plzh(str, iL2, m-1);
            }
        }
    }
}


