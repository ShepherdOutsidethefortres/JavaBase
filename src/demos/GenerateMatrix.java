package demos;

/**
 * 从左到右、从上到下、从右到左、从下到上
 * <p>
 * 从左到右后，上边界+1
 * 从上到下后，右边界-1
 * 从右到左后，下边界-1
 * 从下到上后，左边界+1
 * Created by huiweizhao on 2019/8/26.
 */
public class GenerateMatrix {
    public static void main(String[] args) {
        int[][] matrix = generateMatrix(3);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 生成二位矩阵
     *
     * @param n
     * @return
     */
    public static int[][] generateMatrix(int n) {
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        int num = 1, tar = n * n;

        int[][] mat = new int[n][n];

        while (num <= tar) {
            for (int i = left; i <= right; i++) {
                mat[top][i] = num++;
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                mat[i][right] = num++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                mat[bottom][i] = num++;
            }
            bottom--;

            for (int i = bottom; i >= top; i--) {
                mat[i][left] = num++;
            }
            left++;
        }

        return mat;

    }

}
