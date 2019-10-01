package demos;

/**
 * 选择，时间复杂度O(n2)
 * Created by huiwei.zhao on 2019/7/2.
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {4, 2, 1, 5, 3, 29, 72, 3, 53, 46};
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            if (i != min) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
