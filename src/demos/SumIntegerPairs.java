package demos;

/**
 * 定一个整型数组（数组中的元素可重复，已排序），以及一个指定的值。找出数组中两数之和为指定值的所有整数对，要求时间复杂度为O(N)。
 * 若是无序的，需要先排序，或者暴力法
 * Created by huiwei.zhao on 2019/7/16.
 *
 * (LeeCode原题)给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
    因为 nums[0] + nums[1] = 2 + 7 = 9
    所以返回 [0, 1]
 */
public class SumIntegerPairs {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int sum = 11;

        int[] nums={2,7,11,15};
        int target=9;
        getSum(nums,target);

//        getSumForce(arr,sum);
    }

    public static void getSumForce(int[] arr,int sum){
        System.out.println("Force:");
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i]+arr[j]==sum){
                    System.out.println("left:" + arr[i] + ",right:" + arr[j]);
                }
            }
        }
    }

    public static void getSum(int[] arr, int sum) {
        System.out.println("O(N):");
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] + arr[right] > sum) {
                right--;
            } else if (arr[left] + arr[right] < sum) {
                left++;
            } else {
                System.out.println("left:" + left + ",right:" + right);
                left++;
                right--;
            }
        }
    }
}


