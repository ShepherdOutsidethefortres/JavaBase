package demos;

/**
 * 矩阵旋转
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * <p>
 * 7 4 1
 * 8 5 2
 * 9 6 3
 * Created by huiweizhao on 2019/8/25.
 */
public class MatrixRotate {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},{13,14,15,16}};

        rotate(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf(matrix[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void rotate(int[][] matrix) {
        //矩阵旋转
        //先旋转270
        int len = matrix.length;

        for (int i = 0; i <= len / 2; i++) {
            for (int j = 0; j <= len / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len - j - 1][len - i - 1];
                matrix[len - j - 1][len - i - 1] = temp;
            }
        }

        //再翻转180度
        for (int i = 0; i < len / 2; i++) {
            for (int j = 0; j < len; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len - i - 1][j];
                matrix[len - i - 1][j] = temp;
            }
        }
    }
}
