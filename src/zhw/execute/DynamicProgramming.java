package zhw.execute;

import java.util.Scanner;

public class DynamicProgramming {

    public static void main(String[] args) {
        oneDimension_zeroOnePack();
    }

    /***
     * 二维01背包
     */
    private static void twoDimension_zeroOnePack() {
        final int N = 1010;
        int n;//物品数量
        int m;//背包体积
        int[] v = new int[N];//物品体积
        int[] w = new int[N];//物品价值
        int[][] f = new int[N][N];//价值集合

        //输入物品件数
        //输入背包容量
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        //数据准备
        for (int i = 0; i < n; i++) {
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= v[i]) {
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - v[i]] + w[i]);
                }
            }
        }

        System.out.println(f[n][m]);
    }

    /***
     * 一维优化01背包
     */
    private static void oneDimension_zeroOnePack() {
        final int N = 1010;
        int n;//物品数量
        int m;//背包体积
        int[] v = new int[N];//物品体积
        int[] w = new int[N];//物品价值
        int[] f = new int[N];//价值集合

        //输入物品件数
        //输入背包容量
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        //数据准备
        for (int i = 0; i < n; i++) {
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }

        int[][] path = new int[n + 5][m + 5];

        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= v[i]; j--) {
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
        }

        System.out.println(f[m]);

    }
}
