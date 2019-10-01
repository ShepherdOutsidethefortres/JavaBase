package demos;

/**
 * 将两个有序数组合并成一个有序数组
 * 非递归：1个数组对应一个角标，创建新数组，将比较后的值放在新数组里。
 * Created by huiwei.zhao on 2019/6/24.
 */
public class ArrayContact {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9, 11, 13};
        int[] arr2 = {2, 4, 6, 8, 10, 12};
        int[] merge = merge(arr1, arr1.length, arr2, arr2.length);

        for (int i = 0; i < merge.length; i++) {
            System.out.println(merge[i]);
        }
    }

    /**
     * @param arr1
     * @param m    数组1长度
     * @param arr2
     * @param n    数组2长度
     * @return
     */
    public static int[] merge(int[] arr1, int m, int[] arr2, int n) {
        int[] newArr = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < m && j < n) {
            if (arr1[i] < arr2[j]) {
                newArr[k++] = arr1[i++];
            } else {
                newArr[k++] = arr2[j++];
            }
        }

        if (i >= m) {
            while (j < n) {
                newArr[k++] = arr2[j++];
            }
        }

        if (j >= n) {
            while (i < m) {
                newArr[k++] = arr1[i++];
            }
        }


        return newArr;
    }
}
