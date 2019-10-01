package demos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huiweizhao on 2019/8/26.
 */
public class SpiralOrder {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        spiralOrder(matrix);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> integerList = new ArrayList<>();

        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        int count = 0;

        outer:while (count <= matrix.length * matrix[0].length) {

            for (int i = left; i <= right; i++) {
                System.out.printf(matrix[top][i] + " ");
                count++;
                if (count >= matrix.length * matrix[0].length){
                    break outer;
                }
            }

            top++;

            for (int i = top; i <= bottom; i++) {
                System.out.printf(matrix[i][right] + " ");
                count++;
                if (count >= matrix.length * matrix[0].length){
                    break outer;
                }
            }
            right--;

            for (int i = right; i >= left; i--) {
                System.out.printf(matrix[bottom][i] + " ");
                count++;
                if (count >= matrix.length * matrix[0].length){
                    break outer;
                }
            }

            bottom--;

            for (int i = bottom; i >= top; i--) {
                System.out.printf(matrix[i][left] + " ");
                count++;
                if (count >= matrix.length * matrix[0].length){
                    break outer;
                }
            }
            left++;
        }


        return integerList;
    }
}
