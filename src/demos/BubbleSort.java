package demos;

/**
 * 冒泡排序
 * Created by huiwei.zhao on 2019/7/2.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr1 = {4, 2, 1, 5, 3, 29, 72, 3, 53, 46};
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1.length - 1-i; j++) {
                if (arr1[j] > arr1[j + 1]) {
                    int temp = arr1[j];
                    arr1[j] = arr1[j + 1];
                    arr1[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }

    }
}
