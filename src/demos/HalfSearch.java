package demos;

/**
 * 折半查找，有序数组
 * 猜数字
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
 * <p>
 * -1 : 我的数字比较小
 * 1 : 我的数字比较大
 * 0 : 恭喜！你猜对了！
 */
public class HalfSearch {
    private static int NUM = 6;

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6, 7, 8, 9, 11, 23};

        int x = halfFind(10, arr);
        if (x == -1) {
            System.out.println("查无此元素");
        }

        System.out.printf(".......................");
        System.out.printf(guessNumber(10) + "");
    }

    public static int halfFind(int des, int[] arr) {
        int min = 0;
        int max = arr.length;

        while (min < max) {
            int mid = (min + max) >> 1;
            if (des > arr[mid]) {
                min = mid + 1;
            } else if (des < arr[mid]) {
                max = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static int guessNumber(int n) {
        int min = 0;
        int max = n;
        int count = 0;

        while (min <= max) {
            int mid = (min + max) >> 1;

            count++;
            System.out.println("第" + count + "次猜：" + mid);
            int flag = guess(mid);

            if (flag == -1) {
                max = mid - 1;
            } else if (flag == 1) {
                min = mid + 1;
            } else {
                return mid;
            }
        }

        return -2;
    }

    public static int guess(int num) {
        if (num < NUM) {
            System.out.println("我的数字比较大");
            return 1;
        } else if (num > NUM) {
            System.out.println("我的数字比较小");
            return -1;
        } else {
            System.out.println("恭喜！你猜对了！");
            return 0;
        }
    }
}
