package zhw.execute;


import java.util.ArrayList;
import java.util.List;

public class Find_M_From_N {

    private static List<List<String>> ans = new ArrayList<>();

    public static void main(String[] args) {

        ArrayList<String> inputSkus = new ArrayList<>();

        inputSkus.add("1");
        inputSkus.add("2");
        inputSkus.add("3");
        inputSkus.add("4");
        inputSkus.add("4");

        Find_M_From_N mFromN = new Find_M_From_N();

        mFromN.fun(5,3);

//        mFromN.getCombine(inputSkus.size(), 3, 1, new ArrayList<>(), inputSkus);
//
//        for (int i = 0; i < ans.size(); i++) {
//            List<String> combines = ans.get(i);
//            for (String skuCode : combines) {
//                System.out.printf(skuCode + ",");
//            }
//            System.out.println();
//        }
    }

    /***
     * 获取所有组合的字符串
     * @param n
     * @param k
     * @param start
     * @param list
     * @param inputList
     */
    public void getCombine(int n, int k, int start, List<String> list, List<String> inputList) {
        if (k == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {
            list.add(inputList.get(i - 1));
            getCombine(n, k - 1, i + 1, list, inputList);
            list.remove(list.size() - 1);
        }
    }

    public void fun(int n, int m) {
        int[] dp = new int[m + 1];
        for (int i = 0; i <= m; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[j] += dp[j - 1];
            }
        }
        System.out.println(dp[m]);
    }

}
