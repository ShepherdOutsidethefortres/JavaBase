package demos;

/**
 * 插入排序
 * 1.假定第一个元素是有序的，从第二个元素开始往前比较
 * 2.比较完第二个元素，从第三个元素开始往前比较
 * Created by huiwei.zhao on 2019/7/2.
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {4, 2, 1, 5, 3, 29, 72, 3, 53, 46};

        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            if (j != i) {
                arr[j] = tmp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
}
