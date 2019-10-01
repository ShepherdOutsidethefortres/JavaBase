package demos;

/**
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * <p>
 * 示例1
 * 输入:
 * nums = [1, 7, 4,3, 6, 5, 6,1,3]
 * 输出: 3
 * 解释:
 * 索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * <p>
 * 示例2
 * 输入:
 * nums = [1, 2, 3]
 * 输出: -1
 * 解释:
 * 数组中不存在满足此条件的中心索引。
 * Created by huiweizhao on 19/8/23.
 */
public class ArrayPivotIndex {
    public static void main(String[] args) {
        int[] nums = {1, 7, 3,4, 6, 5, 6,1,3};
        System.out.printf("pivotIndex is:" + pivotIndex(nums));

    }

    public static int pivotIndex(int[] nums) {
        int sum = 0;
        int i = 0;
        while (i < nums.length) {
            sum += nums[i];
            i++;
        }

        int avgSum = sum / 2;
        i = -1;

        while (i < nums.length) {
            i++;
            avgSum -= nums[i];

            if (avgSum < 0) {
                break;
            }
        }

        //中心索引
        int pivotIndex = i;

        //如果去掉中心索引后，计算结果能正常，说明正确
        avgSum = (sum - nums[pivotIndex]) / 2;
        i = -1;
        while (i < nums.length) {
            i++;
            avgSum -= nums[i];

            if (avgSum > 0) {
                continue;
            } else if (avgSum < 0) {
                return -1;
            } else {
                return pivotIndex;
            }
        }

        return -1;
    }
}
